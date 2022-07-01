package com.example.currencyconverter.ui.list_currency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.ItemCurrencyBinding
import com.example.currencyconverter.models.Currency
import java.text.DecimalFormat


class CurrencyAdapter(
    private val actionListener: CurrencyActionListener,
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    var currencyItems: List<Currency> = mutableListOf()

    fun setList(list: List<Currency>) {
        currencyItems = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCurrencyBinding.inflate(inflater, parent, false)


        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencyItems[position])
    }

    override fun getItemCount(): Int {
        return currencyItems.size
    }

    inner class CurrencyViewHolder(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Currency) = binding.run {
            rateName.text = item.name
            rateValue.text = DecimalFormat("#0.0000").format(item.value)
            if (item.isFavorite) {
                favorite.setImageResource(R.drawable.star_pressed)
            } else {
                favorite.setImageResource(R.drawable.star)
            }

            root.setOnClickListener {
                actionListener.currencyExchange(item)
            }

            favorite.setOnClickListener {
                actionListener.onCurrencyFavorite(item.copy(isFavorite = !item.isFavorite))
            }

            root.setOnLongClickListener {
                actionListener.currencyUp(item)

                true
            }
        }
    }


}