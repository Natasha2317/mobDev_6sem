package com.example.currencyconverter.ui.list_currency.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.data.room.CurrencyLocal
import com.example.currencyconverter.databinding.ItemCurrencyBinding
import com.example.currencyconverter.models.Currency


class CurrencyAdapter(
    private val actionListener: CurrencyActionListener,
    var currencyItems: MutableList<Currency>
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>(){
//    var currencyItems: List<Currency> = emptyList()
//    var currencyLocalItems: List<CurrencyLocal> = emptyList()
//    private var isCurrentFavorite: Boolean = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCurrencyBinding.inflate(inflater, parent, false)


        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        var currencyItem: Currency = currencyItems[position]
        with(holder.binding){

            rateName.text = currencyItem.name
            rateValue.text = currencyItem.value.toString()
            if (currencyItem.isFavorite) {
                favorite.setImageResource(R.drawable.star_pressed)
            } else {
                favorite.setImageResource(R.drawable.star)
            }

        }
    }

    override fun getItemCount(): Int {
        return currencyItems.size
    }

    inner class CurrencyViewHolder(val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root)

    fun setList(list: MutableList<Currency>){
        currencyItems = list
//        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: CurrencyViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            actionListener.currencyExchange(currencyItems[holder.absoluteAdapterPosition])
        }

        holder.binding.favorite.setOnClickListener {
            currencyItems[holder.absoluteAdapterPosition].isFavorite = !currencyItems[holder.absoluteAdapterPosition].isFavorite
            actionListener.onCurrencyFavorite(currencyItems[holder.absoluteAdapterPosition])
            notifyDataSetChanged()

        }

    }

}