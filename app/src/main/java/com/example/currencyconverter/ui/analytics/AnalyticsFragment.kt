package com.example.currencyconverter.ui.analytics

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.currencyconverter.data.api.repository.RetrofitRepository
import com.example.currencyconverter.databinding.FragmentAnalyticsBinding
import com.example.currencyconverter.ui.analytics.viewmodel.AnalyticsViewModel
import com.example.currencyconverter.ui.analytics.viewmodel.AnalyticsViewModelFactory


class AnalyticsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = AnalyticsFragment()
    }
    private lateinit var binding: FragmentAnalyticsBinding
    private lateinit var viewModel: AnalyticsViewModel
    private var currencyRepository: RetrofitRepository = RetrofitRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = AnalyticsViewModelFactory(currencyRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[AnalyticsViewModel::class.java]
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
