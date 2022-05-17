package com.example.currencyconverter.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.FragmentMainBinding
import com.example.currencyconverter.ui.analytics.AnalyticsFragment
import com.example.currencyconverter.ui.history.HistoryFragment
import com.example.currencyconverter.ui.list_currency.ListFragment

import com.google.android.material.tabs.TabLayout


class MainFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
    private lateinit var binding: FragmentMainBinding

    var fragmentsList = listOf(
        ListFragment.newInstance(),
        HistoryFragment.newInstance(),
        AnalyticsFragment.newInstance()
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .add(R.id.container_fragment, ListFragment.newInstance())
                .commitNow()
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                childFragmentManager.beginTransaction()
                    .replace(R.id.container_fragment, fragmentsList[tab?.position!!])
                    .commitNow()
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        return binding.root
    }



}


