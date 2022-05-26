package com.example.currencyconverter.ui.list_currency

import android.os.Bundle
import android.text.TextUtils.indexOf
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.data.room.RepositoryInitialization
import com.example.currencyconverter.ui.list_currency.adapter.CurrencyAdapter
import com.example.currencyconverter.databinding.FragmentListBinding
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.ui.exchange.ExchangeFragment
import com.example.currencyconverter.ui.list_currency.adapter.CurrencyActionListener
import com.example.currencyconverter.ui.list_currency.viewmodel.MainViewModel
import com.example.currencyconverter.ui.list_currency.viewmodel.MainViewModelFactory


class ListFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()

    }
    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CurrencyAdapter
    private lateinit var recyclerView: RecyclerView
    private var currencyItems: MutableList<Currency> = mutableListOf()
    lateinit var currentCurrency: Currency
    lateinit var currentCurrencyUp: Currency

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = MainViewModelFactory(RepositoryInitialization.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        val fragment = ExchangeFragment()
        val bundle = Bundle()

        adapter = CurrencyAdapter(object: CurrencyActionListener {
            override fun onCurrencyFavorite(currency: Currency) {
                currentCurrency = currency
                viewModel.updateListFavoriteCurrency(currentCurrency){}
            }
            override fun currencyExchange(currency: Currency) {
                bundle.putSerializable("currency", currency)
                fragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .add(R.id.container_fragment, fragment)
                    .commitNow()
            }
            var currencyType = 0
            override fun currencyUp(currencyUp: Currency) {
                if (currencyType==0){
                    var index = currencyItems.indexOf(currencyUp)
                    currencyItems.removeAt(index)
                    currencyItems.add(0, currencyUp)
                    adapter.setList(currencyItems)
                    bundle.putSerializable("currencyUp", currencyUp)
                    println(bundle.putSerializable("currencyUp", currencyUp))
                    currencyType = 1
                }else {
                    bundle.putSerializable("currency", currencyUp)
                    fragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .add(R.id.container_fragment, fragment)
                        .commitNow()
                }

            }
        }, currencyItems)

        getLocalCurrencyList()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.updateList.setOnClickListener {
            viewModel.getRetrofitCurrencyList()
            viewModel.data.observe(viewLifecycleOwner) { it ->
                binding.date.text = it.date
                var rates = it.rates
                for(item in rates){
                    viewModel.updateListCurrency(item) {
                    }
                }
                getLocalCurrencyList()
            }
        }

    }

    private fun getLocalCurrencyList() {

        viewModel.getLocalCurrencyList().let { newCurrency ->
            currencyItems.clear()
            newCurrency.forEach { currency ->
                currencyItems.add(currency)
            }
            adapter.setList(currencyItems)
        }
    }


}


