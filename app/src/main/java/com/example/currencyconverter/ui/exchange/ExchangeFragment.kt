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
import com.example.currencyconverter.data.room.repository.RepositoryRealization
import com.example.currencyconverter.databinding.FragmentExchangeBinding
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.ui.exchange.viewmodel.ExchangeViewModel
import com.example.currencyconverter.ui.exchange.viewmodel.ExchangeViewModelFactory
import com.example.currencyconverter.ui.list_currency.ListFragment


class ExchangeFragment() : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ExchangeFragment()
    }

    private lateinit var binding: FragmentExchangeBinding
    private lateinit var viewModel: ExchangeViewModel
    private var currencyRepository: RetrofitRepository = RetrofitRepository()
    lateinit var currentCurrency: Currency


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
        val firstName = currentCurrency.name
        viewModel.getLocalCurrencyList().let { newCurrency ->
            newCurrency.forEach { currency ->
                if (currency.name == firstName){
                    binding.rateName.text = currency.name
                }
            }
        }

//        viewModel.data.observe(viewLifecycleOwner) { it ->
//            val name = currentCurrency.name
//            for (item in it.rates){
//                if (item.name == name){
//                    binding.rateName.text = item.name
//                }
//                binding.valueFirstCurrency.addTextChangedListener(object : TextWatcher {
//                    override fun afterTextChanged(s: Editable) {}
//                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                        if (start>0){
//                            val num1: Float = s.toString().toFloat()
//                            binding.valueSecondCurrency.text = (num1 * item.value).toString()
//                        }else{
//                            binding.valueSecondCurrency.text = "1"
//                        }
//
//                    }
//                })
//            }



        binding.backToListFragment.setOnClickListener {
            val fragment = ListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_fragment, fragment)
                .commitNow()
        }

    }


}
