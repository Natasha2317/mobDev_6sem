package com.example.currencyconverter.ui.filters

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.data.api.repository.RetrofitRepository
import com.example.currencyconverter.databinding.FragmentFiltersBinding
import com.example.currencyconverter.ui.filters.viewmodel.FiltersViewModel
import com.example.currencyconverter.ui.filters.viewmodel.FiltersViewModelFactory


class FiltersFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = FiltersFragment()
    }
    private lateinit var binding: FragmentFiltersBinding
    private lateinit var viewModel: FiltersViewModel
    private var currencyRepository: RetrofitRepository = RetrofitRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = FiltersViewModelFactory(currencyRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[FiltersViewModel::class.java]
        viewModel.init()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFiltersBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner) {

        }
        var checked = false
        if (!checked) {
            binding.allTime.setOnClickListener {
                if (!checked) {
                    binding.allTime.setBackgroundColor(Color.BLACK)
                    checked = true
                }
            }
        }
        if (!checked) {
            binding.week.setOnClickListener {
                if (!checked) {
                    binding.week.setBackgroundColor(0xFF00FF00.toInt())
                    checked = true
                }

            }
        }
        if (!checked) {
            binding.month.setOnClickListener {
                if (!checked) {
                    binding.month.setBackgroundColor(0xFF00FF00.toInt())
                    checked = true
                }
            }

        }
        if (!checked) {
            binding.userDate.setOnClickListener {
                if (!checked) {
                    binding.userDate.setBackgroundColor(0xFF00FF00.toInt())
                    checked = true
                }
            }
        }
    }


}
