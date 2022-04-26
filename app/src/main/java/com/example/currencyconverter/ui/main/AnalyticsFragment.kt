package com.example.currencyconverter.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.currencyconverter.DependencyInjection
import com.example.currencyconverter.repository.CurrencyRepository
import com.example.currencyconverter.databinding.FragmentAnalyticsBinding
import com.example.currencyconverter.viewmodels.MainViewModel
import com.example.currencyconverter.viewmodels.MainViewModelFactory


class AnalyticsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = AnalyticsFragment()
    }
    private lateinit var binding: FragmentAnalyticsBinding
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
        binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner){

        }

    }

}
