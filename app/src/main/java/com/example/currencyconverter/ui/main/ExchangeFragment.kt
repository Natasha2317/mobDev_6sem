package com.example.currencyconverter.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.DependencyInjection
import com.example.currencyconverter.databinding.FragmentExchangeBinding
import com.example.currencyconverter.repository.CurrencyRepository
import com.example.currencyconverter.viewmodels.MainViewModel
import com.example.currencyconverter.viewmodels.MainViewModelFactory


class ExchangeFragment() : Fragment() {

//    val currency: Currency
//        get() = requireArguments().getSerializable(ARG_CURRENCY) as Currency

    companion object {
        @JvmStatic
        fun newInstance() = ExchangeFragment()
    }

    private lateinit var binding: FragmentExchangeBinding
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
        binding = FragmentExchangeBinding.inflate(inflater, container, false)
//        if (arguments?.getString("currency") != null){
//            var name = requireArguments().getString("currency")
////            binding.rateName.text = name
//        }
        binding.valueSecondCurrency.text = "1"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner) { it ->
            val name = arguments?.getString("currency")
            for (item in it.rates){
                if (item.name == name){
                    binding.rateName.text = item.name
                }
                binding.valueFirstCurrency.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable) {}
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        if (start>=0){
                            val num1: Float = s.toString().toFloat()
                            binding.valueSecondCurrency.text = (num1 * item.value).toString()
                        }else{
                            binding.valueSecondCurrency.text = "1"
                        }

                    }
                })
            }

        }



    }


}
