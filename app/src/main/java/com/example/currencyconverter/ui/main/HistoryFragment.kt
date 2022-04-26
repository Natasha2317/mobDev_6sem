package com.example.currencyconverter.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.currencyconverter.DependencyInjection
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.FragmentFiltersBinding
import com.example.currencyconverter.repository.CurrencyRepository

import com.example.currencyconverter.databinding.FragmentHistoryBinding
import com.example.currencyconverter.viewmodels.MainViewModel
import com.example.currencyconverter.viewmodels.MainViewModelFactory


class HistoryFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var viewModel: MainViewModel
    private var currencyRepository: CurrencyRepository = DependencyInjection.repository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = MainViewModelFactory(currencyRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.init()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        binding.filters.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_fragment, FiltersFragment())
                .commitNow()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner){it ->
            for (item in it.rates){
                binding.rateName.text = item.name
                binding.rateValue.text = item.value.toString()
            }
        }

    }

}
