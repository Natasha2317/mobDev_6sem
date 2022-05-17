package com.example.currencyconverter.ui.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.currencyconverter.R
import com.example.currencyconverter.data.api.repository.RetrofitRepository

import com.example.currencyconverter.databinding.FragmentHistoryBinding
import com.example.currencyconverter.ui.filters.FiltersFragment
import com.example.currencyconverter.ui.history.viewmodel.HistoryViewModel
import com.example.currencyconverter.ui.history.viewmodel.HistoryViewModelFactory


class HistoryFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var viewModel: HistoryViewModel
    private var currencyRepository: RetrofitRepository = RetrofitRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = HistoryViewModelFactory(currencyRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]
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
