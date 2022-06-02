package com.example.currencyconverter.ui.list_currency

import android.os.Bundle
import android.text.TextUtils.indexOf
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker

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
import java.text.SimpleDateFormat
import java.time.DateTimeException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.ZoneId
import java.util.*


class ListFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()

    }
    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CurrencyAdapter
    private lateinit var recyclerView: RecyclerView
    lateinit var currentCurrency: Currency

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = MainViewModelFactory(RepositoryInitialization.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        val fragment = ExchangeFragment()
        val bundle = Bundle()
        var currencyType = 0
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

            override fun currencyUp(currencyUp: Currency) {
                if (currencyType==0){
                    viewModel.longClickExchange(currencyUp)
                    bundle.putSerializable("currencyUp", currencyUp)
                    currencyType = 1
                }else {
                    bundle.putSerializable("currency", currencyUp)
                    fragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .add(R.id.container_fragment, fragment)
                        .commitNow()
                }

            }
        })

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
        binding.date.text = getCurrentDate()
        binding.updateList.setOnClickListener {
            viewModel.update()
        }

        viewModel.data.observe(viewLifecycleOwner) { list ->
            adapter.setList(list)
        }
    }

    private fun getCurrentDate(): String {
        val c = Calendar.getInstance()
        val df = SimpleDateFormat("dd-MM-yyyy HH-mm-ss")
        return df.format(c.time)
    }

}


