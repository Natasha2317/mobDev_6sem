package com.example.currencyconverter.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.ItemCurrencyBinding
import com.example.currencyconverter.models.Currency
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlin.coroutines.coroutineContext


class CurrencyAdapter(
    private val actionListener: CurrencyActionListener
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>(), View.OnClickListener{
    var currencyItems: List<Currency> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCurrencyBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.rateName.setOnClickListener(this)
        binding.favorite.setOnClickListener {
            DrawableCompat.setTint(binding.favorite.drawable, ContextCompat.getColor(parent.context, R.color.currency_value))
        }

        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        var currencyItem: Currency = currencyItems[position]
        with(holder.binding){
            holder.itemView.tag = currencyItem
            rateName.tag = currencyItem

            rateName.text = currencyItem.name
            rateValue.text = currencyItem.value.toString()

        }
    }

    override fun getItemCount(): Int {
        return currencyItems.size
    }

    inner class CurrencyViewHolder(val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {
        val currency = v.tag as Currency
        when(v.id){
            R.id.favorite ->{
                actionListener.onCurrencyFavorite(currency)
            }else ->{
                actionListener.currencyExchange(currency)
            }
        }
    }
}