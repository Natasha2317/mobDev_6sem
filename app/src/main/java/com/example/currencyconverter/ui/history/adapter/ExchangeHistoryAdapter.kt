package com.example.currencyconverter.ui.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.databinding.ItemExchangeHistoryBinding
import com.example.currencyconverter.models.ExchangeHistory
import java.text.DecimalFormat


class ExchangeHistoryAdapter(

) : RecyclerView.Adapter<ExchangeHistoryAdapter.ExchangeHistoryViewHolder>(){

    private var exchangeHistoryItems: MutableList<ExchangeHistory> = mutableListOf()

    fun setList(list: MutableList<ExchangeHistory>){
        exchangeHistoryItems = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemExchangeHistoryBinding.inflate(inflater, parent, false)

        return ExchangeHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExchangeHistoryViewHolder, position: Int) {
        holder.bind(exchangeHistoryItems[position])
    }

    override fun getItemCount(): Int {
        return exchangeHistoryItems.size
    }

    inner class ExchangeHistoryViewHolder(private val binding: ItemExchangeHistoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ExchangeHistory) = binding.run {
            nameCurrency1.text = item.name_currency1
            nameCurrency2.text = item.name_currency2
            valueCurrency1.text = DecimalFormat("#0.0000").format(item.value_currency1)
            valueCurrency2.text = DecimalFormat("#0.0000").format(item.value_currency2)
            data.text = item.exchange_date
        }
    }

}