package com.example.currencyconverter.ui.filters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
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

        val spinner: Spinner = binding.spinner

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var filter = parent?.getItemAtPosition(position).toString()
                val fragment = HistoryFragment()
                val bundle = Bundle()
                bundle.putString("filter", filter)
                fragment.arguments = bundle
                binding.bntFilter.setOnClickListener {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container_fragment, fragment)
                        .commitNow()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

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
