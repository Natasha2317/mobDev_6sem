package com.example.currencyconverter.ui.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.ItemExchangeHistoryBinding
import com.example.currencyconverter.models.ExchangeHistory
import java.text.DecimalFormat


class ExchangeHistoryAdapter(
    var exchangeHistoryItems: MutableList<ExchangeHistory>
) : RecyclerView.Adapter<ExchangeHistoryAdapter.ExchangeHistoryViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemExchangeHistoryBinding.inflate(inflater, parent, false)

        return ExchangeHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExchangeHistoryViewHolder, position: Int) {
        var exchangeHistoryItem: ExchangeHistory = exchangeHistoryItems[position]
        with(holder.binding){

            nameCurrency1.text = exchangeHistoryItem.name_currency1
            nameCurrency2.text = exchangeHistoryItem.name_currency2
            valueCurrency1.text = DecimalFormat("#0.0000").format(exchangeHistoryItem.value_currency1)
            valueCurrency2.text = DecimalFormat("#0.0000").format(exchangeHistoryItem.value_currency2)
            data.text = exchangeHistoryItem.exchange_date

        }
    }

    override fun getItemCount(): Int {
        return exchangeHistoryItems.size
    }

    inner class ExchangeHistoryViewHolder(val binding: ItemExchangeHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    fun setList(list: MutableList<ExchangeHistory>){
        exchangeHistoryItems = list
        notifyDataSetChanged()
    }

}