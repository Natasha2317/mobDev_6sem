package com.example.currencyconverter.ui.exchange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.R
import com.example.currencyconverter.data.api.repository.RetrofitRepository
import com.example.currencyconverter.data.room.RepositoryInitialization
import com.example.currencyconverter.databinding.FragmentExchangeBinding
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.models.ExchangeHistory
import com.example.currencyconverter.ui.exchange.viewmodel.ExchangeViewModel
import com.example.currencyconverter.ui.exchange.viewmodel.ExchangeViewModelFactory
import com.example.currencyconverter.ui.list_currency.ListFragment
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


class ExchangeFragment() : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ExchangeFragment()
    }

    private lateinit var binding: FragmentExchangeBinding
    private lateinit var viewModel: ExchangeViewModel
    private lateinit var currentCurrency: Currency
    lateinit var currentCurrencyUp: Currency



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val viewModelFactory = ExchangeViewModelFactory(RepositoryInitialization.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)[ExchangeViewModel::class.java]
//        viewModel.init()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentExchangeBinding.inflate(inflater, container, false)

        currentCurrency = arguments?.getSerializable("currency") as Currency
        binding.valueSecondCurrency.text = "1"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var firstName = currentCurrency.name
        var firstValue = currentCurrency.value
        var secondName = ""
        var secondNameIs = false
        var secondValue = 1.0000
        var num1 = 1.0000
        var valueSecondCurrency = 1.0000
        val exchangeHistorySize = viewModel.getExchangeHistory()

        if (arguments?.getSerializable("currencyUp") != null){
            var currentCurrencyUp = arguments?.getSerializable("currencyUp") as Currency
            secondName = currentCurrency.name
            secondValue = currentCurrency.value
            firstName = currentCurrencyUp.name
            firstValue = currentCurrencyUp.value
            binding.rateName1.text = secondName
            binding.rateName.text = firstName
        }else{
            viewModel.getFavoriteCurrencyList().let { newCurrency ->
                newCurrency?.forEach { currency ->
                    if(!secondNameIs){
                        if (currency.name != firstName){
                            currentCurrencyUp = currency
                            secondName = currency.name
                            secondValue = currency.value
                            binding.rateName1.text = secondName
                            if (currency.isFavorite) {
                                binding.favorite1.setImageResource(R.drawable.star_pressed)
                            } else {
                                binding.favorite1.setImageResource(R.drawable.star)
                            }
                            secondNameIs = true
                        }
                    }
                }
            }
            binding.rateName.text = firstName
        }


        if (currentCurrency.isFavorite) {
            binding.favorite.setImageResource(R.drawable.star_pressed)
        } else {
            binding.favorite.setImageResource(R.drawable.star)
        }
        binding.valueFirstCurrency.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (start>=0){
                    num1 = s.toString().toDouble()
                    valueSecondCurrency = (firstValue * num1) / secondValue
                    binding.valueSecondCurrency.text = DecimalFormat("#0.0000").format(valueSecondCurrency)
                }else{
                    valueSecondCurrency = 1.0000
                    binding.valueSecondCurrency.text = DecimalFormat("#0.0000").format(valueSecondCurrency)
                }

                var itemExchangeHistory = ExchangeHistory(
                    exchangeHistorySize.size + 1,
                    firstName,
                    secondName,
                    num1,
                    valueSecondCurrency,
                    getCurrentDate()
                )

                binding.exchange.setOnClickListener {
                    viewModel.addExchangeHistory(itemExchangeHistory){}
                    val fragment = ListFragment()
                    val bundle = Bundle()
                    fragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container_fragment, fragment)
                        .commitNow()
                }
            }

        })



        binding.backToListFragment.setOnClickListener {
            val fragment = ListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_fragment, fragment)
                .commitNow()
        }



    }

    fun getCurrentDate(): String {
        val c = Calendar.getInstance()
        val df = SimpleDateFormat("dd-MM-yyyy")
        return df.format(c.time)
    }




}
