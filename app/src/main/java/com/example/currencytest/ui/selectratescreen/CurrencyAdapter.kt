package com.example.currencytest.ui.selectratescreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currencytest.R
import com.example.currencytest.db.ConvertCurrency

interface ListCallback {
    fun onItemClicked(currency: ConvertCurrency)
}

class CurrencyAdapter(private val callback: ListCallback) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {
    private val list = mutableListOf<ConvertCurrency>()

    inner class CurrencyHolder(val v: View) : RecyclerView.ViewHolder(v) {
        init {
            v.setOnClickListener { callback.onItemClicked(list[adapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        return CurrencyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val currency = list[position]
        (holder.v as TextView).text = currency.code
    }

    fun setList(newList: List<ConvertCurrency>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}