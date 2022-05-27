package com.example.currencyconverter.ui.filters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.R
import com.example.currencyconverter.data.room.RepositoryInitialization
import com.example.currencyconverter.databinding.FragmentFiltersBinding
import com.example.currencyconverter.ui.filters.viewmodel.FiltersViewModel
import com.example.currencyconverter.ui.filters.viewmodel.FiltersViewModelFactory
import com.example.currencyconverter.ui.history.HistoryFragment


class FiltersFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = FiltersFragment()
    }
    private lateinit var binding: FragmentFiltersBinding
    private lateinit var viewModel: FiltersViewModel
    private val filtersList: List<String> = listOf("Все время", "Месяц", "Неделя", "Выбрать")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = FiltersViewModelFactory(RepositoryInitialization.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)[FiltersViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFiltersBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filters = resources.getStringArray(R.array.filters)
        val spinner: Spinner = binding.spinner

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

//        var checked = false
//        if (!checked) {
//            binding.allTime.setOnClickListener {
//                if (!checked) {
//                    binding.allTime.setBackgroundColor(R.drawable.button_pressed)
//                    checked = true
//                }else
//                    binding.allTime.setBackgroundColor(R.drawable.button_not_pressed)
//            }
//        }
//        if (!checked) {
//            binding.week.setOnClickListener {
//                if (!checked) {
//                    binding.allTime.setBackgroundColor(R.drawable.button_pressed)
//                    checked = true
//                }else
//                    binding.allTime.setBackgroundColor(R.drawable.button_not_pressed)
//            }
//        }
//        if (!checked) {
//            binding.month.setOnClickListener {
//                if (!checked) {
//                    binding.allTime.setBackgroundColor(R.drawable.button_pressed)
//                    checked = true
//                }else
//                    binding.allTime.setBackgroundColor(R.drawable.button_not_pressed)
//            }
//
//        }
//        if (!checked) {
//            binding.userDate.setOnClickListener {
//                if (!checked) {
//                    binding.allTime.setBackgroundColor(R.drawable.button_pressed)
//                    checked = true
//                }else
//                    binding.allTime.setBackgroundColor(R.drawable.button_not_pressed)
//            }
//        }

        binding.backToHistoryFragment.setOnClickListener {
            val fragment = HistoryFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_fragment, fragment)
                .commitNow()
        }
    }



}
