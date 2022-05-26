package com.example.currencyconverter.ui.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.data.api.repository.RetrofitRepository
import com.example.currencyconverter.data.room.RepositoryInitialization

import com.example.currencyconverter.databinding.FragmentHistoryBinding
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.models.ExchangeHistory
import com.example.currencyconverter.ui.filters.FiltersFragment
import com.example.currencyconverter.ui.history.adapter.ExchangeHistoryAdapter
import com.example.currencyconverter.ui.history.viewmodel.HistoryViewModel
import com.example.currencyconverter.ui.history.viewmodel.HistoryViewModelFactory
import com.example.currencyconverter.ui.list_currency.adapter.CurrencyAdapter


class HistoryFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var viewModel: HistoryViewModel
    private lateinit var adapter: ExchangeHistoryAdapter
    private lateinit var recyclerView: RecyclerView
    var exchangeHistoryItems: MutableList<ExchangeHistory> = mutableListOf()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = HistoryViewModelFactory(RepositoryInitialization.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]

        adapter = ExchangeHistoryAdapter(exchangeHistoryItems)
        getExchangeHistory()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        binding.filters.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_fragment, FiltersFragment())
                .commitNow()
        }

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.data.observe(viewLifecycleOwner){it ->
//            for (item in it.rates){
//                binding.rateName.text = item.name
//                binding.rateValue.text = item.value.toString()
//            }
//        }

    }

    private fun getExchangeHistory() {

        viewModel.getExchangeHistory().let { newExchangeHistory ->
            exchangeHistoryItems.clear()
            newExchangeHistory.forEach { item ->
                exchangeHistoryItems.add(item)
            }
            adapter.setList(exchangeHistoryItems)
        }
    }

}
