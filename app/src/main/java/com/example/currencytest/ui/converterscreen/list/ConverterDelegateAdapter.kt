package com.example.currencytest.ui.converterscreen.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencytest.R
import kotlinx.android.synthetic.main.converter_item.view.*


class ConverterDelegateAdapter :
    DelegateAdapter<RateItem, ConverterDelegateAdapter.ConverterViewHolder>(
        RateItem::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ConverterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.converter_item, parent, false)
        )

    override fun bindViewHolder(model: RateItem, viewHolder: ConverterViewHolder) {
        viewHolder.bind(model)
    }

    inner class ConverterViewHolder(private val v: View) :
        RecyclerView.ViewHolder(v) {
        fun bind(model: RateItem) {
            v.baseCurTitle.text = model.content().baseCurrency.code
            v.secondCurTitle.text = model.content().second.code
            v.secondCurValue.text = model.content().convertValue.toString()
        }



    }
}