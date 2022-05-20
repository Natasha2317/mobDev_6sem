package com.example.currencyconverter.ui.list_currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.data.room.RepositoryInitialization
import com.example.currencyconverter.data.room.repository.RepositoryRealization
import com.example.currencyconverter.ui.list_currency.adapter.CurrencyAdapter
import com.example.currencyconverter.databinding.FragmentListBinding
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.ui.exchange.ExchangeFragment
import com.example.currencyconverter.ui.list_currency.adapter.CurrencyActionListener
import com.example.currencyconverter.ui.list_currency.adapter.CurrencyFavoriteAdapter
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
    private lateinit var adapterFavorite: CurrencyFavoriteAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewFavorite: RecyclerView
    private var currencyItems: MutableList<Currency> = mutableListOf()
    lateinit var currentCurrency: Currency

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = MainViewModelFactory(RepositoryInitialization.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        adapterFavorite = CurrencyFavoriteAdapter(object: CurrencyActionListener {
                override fun onCurrencyFavorite(currency: Currency) {
                    currentCurrency = currency

                    viewModel.updateListFavoriteCurrency(currentCurrency){
                        getLocalCurrencyList()
                    }
                    viewModel.insertFavoriteCurrency(currentCurrency){
                        getLocalCurrencyList()
                    }
                }
                override fun currencyExchange(currency: Currency) {
                    val fragment = ExchangeFragment()
                    val bundle = Bundle()
                    bundle.putSerializable("currency", currency)
                    fragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .add(R.id.container_fragment, fragment)
                        .commitNow()
                }
            }
        )

        adapter = CurrencyAdapter(object: CurrencyActionListener {
            override fun onCurrencyFavorite(currency: Currency) {
                currentCurrency = currency
                viewModel.updateListFavoriteCurrency(currentCurrency){
                        getLocalCurrencyList()
                    }
            }
            override fun currencyExchange(currency: Currency) {
                val fragment = ExchangeFragment()
                val bundle = Bundle()
                bundle.putSerializable("currency", currency)
                fragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .add(R.id.container_fragment, fragment)
                    .commitNow()
            }
        }, currencyItems)

        getLocalCurrencyList()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        recyclerViewFavorite = binding.recyclerViewFavorite
        recyclerViewFavorite.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerViewFavorite.adapter = adapterFavorite

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


