package com.example.currencyconverter.ui.list_currency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.data.room.CurrencyLocal
import com.example.currencyconverter.databinding.ItemCurrencyBinding
import com.example.currencyconverter.models.Currency
import kotlin.properties.Delegates


class CurrencyFavoriteAdapter(
    private val actionListener: CurrencyActionListener
) : RecyclerView.Adapter<CurrencyFavoriteAdapter.CurrencyViewHolder>() {
    var currencyItems: List<Currency> = emptyList()
    var isCurrentFavorite: Boolean = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCurrencyBinding.inflate(inflater, parent, false)


            if(!isCurrentFavorite){
                DrawableCompat.setTint(binding.favorite.drawable, ContextCompat.getColor(parent.context, R.color.currency_value))
            }
            else{
                DrawableCompat.setTint(binding.favorite.drawable, ContextCompat.getColor(parent.context, R.color.disabled_star))
            }

        return CurrencyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        var currencyItem = currencyItems[position]
        with(holder.binding){

            rateName.text = currencyItem.name
            rateValue.text = currencyItem.value.toString()

        }
    }

    override fun getItemCount(): Int {
        return currencyItems.size
    }

    inner class CurrencyViewHolder(val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root)

    fun setList(list: List<Currency>){
        currencyItems = list
        notifyDataSetChanged()
    }



//    override fun onClick(v: View) {
//        val currency = v.tag as Currency
//        when(v.id){
//            R.id.favorite ->{
//                actionListener.onCurrencyFavorite(currency)
//            }else ->{
//                actionListener.currencyExchange(currency)
//            }
//        }
//    }
    override fun onViewAttachedToWindow(holder: CurrencyViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {

        }
    }


}