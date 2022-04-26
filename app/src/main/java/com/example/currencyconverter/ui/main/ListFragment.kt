package com.example.currencyconverter.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.DependencyInjection
import com.example.currencyconverter.R
import com.example.currencyconverter.adapter.CurrencyActionListener
import com.example.currencyconverter.adapter.CurrencyAdapter
import com.example.currencyconverter.databinding.FragmentListBinding
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.repository.CurrencyRepository
import com.example.currencyconverter.viewmodels.MainViewModel
import com.example.currencyconverter.viewmodels.MainViewModelFactory
import com.example.currencyconverter.viewmodels.RepositoryInitializer


class ListFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: MainViewModel
//    private var currencyRepository: CurrencyRepository = DependencyInjection.repository
    private lateinit var adapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val viewModelFactory = MainViewModelFactory(currencyRepository)
//        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]


        val viewModelFactory = MainViewModelFactory(RepositoryInitializer.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.init()


        adapter = CurrencyAdapter(object: CurrencyActionListener{
            override fun onCurrencyFavorite(currency: Currency) {

            }

            override fun currencyExchange(currency: Currency) {
                val fragment = ExchangeFragment()
                val bundle = Bundle()
                bundle.putString("currency", currency.name)
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


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner) { it ->
            binding.date.text = it.date
            adapter.currencyItems = it.rates
            binding.recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            binding.recyclerView.adapter = adapter

        }
    }

}


