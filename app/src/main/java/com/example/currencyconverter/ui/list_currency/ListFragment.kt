package com.example.currencyconverter.ui.list_currency

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.data.api.repository.RetrofitRepository
import com.example.currencyconverter.data.room.CurrencyLocal
import com.example.currencyconverter.ui.list_currency.adapter.CurrencyAdapter
import com.example.currencyconverter.databinding.FragmentListBinding
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.ui.exchange.ExchangeFragment
import com.example.currencyconverter.ui.list_currency.adapter.CurrencyActionListener
import com.example.currencyconverter.ui.list_currency.adapter.CurrencyFavoriteAdapter
import com.example.currencyconverter.ui.list_currency.viewmodel.MainViewModel


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
    lateinit var currentCurrency: Currency
    private var currencyRepository: RetrofitRepository = RetrofitRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val viewModelFactory = MainViewModelFactory(currencyRepository, Application())
//        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.initDataBase()
        viewModel.getLocalCurrencyList()
        viewModel.getRetrofitCurrencyList()
        adapterFavorite = CurrencyFavoriteAdapter(object: CurrencyActionListener {
                override fun onCurrencyFavorite(currency: Currency) {
                    currentCurrency = currency
                    viewModel.insertFavoriteCurrency(currentCurrency){}
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
//        viewModel.insertFavoriteCurrency(currentCurrency){}

        adapter = CurrencyAdapter(object: CurrencyActionListener {
            override fun onCurrencyFavorite(currency: Currency) {
                currentCurrency = currency
                viewModel.insertFavoriteCurrency(currentCurrency){}
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
        })

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

        viewModel.data.observe(viewLifecycleOwner) { it ->
            binding.date.text = it.date
            adapter.setList(it.rates)
            var rates = it.rates
            for(item in rates){
                viewModel.updateListCurrency(item) {
                }
            }
//            adapter.currencyItems = it.rates
            binding.updateList.setOnClickListener {

            }
        }
        viewModel.getLocalCurrencyList().observe(viewLifecycleOwner) {
            adapterFavorite.setList(it)
        }


    }



}


