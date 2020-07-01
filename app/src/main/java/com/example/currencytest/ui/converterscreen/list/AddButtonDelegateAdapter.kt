package com.example.currencytest.ui.converterscreen.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencytest.R

class AddButtonDelegateAdapter(private val clickListener: View.OnClickListener) :
    DelegateAdapter<AddButtonItem, AddButtonDelegateAdapter.AddButtonViewHolder>(
        AddButtonItem::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        AddButtonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.add_button, parent, false)
        )

    override fun bindViewHolder(
        model: AddButtonItem,
        viewHolder: AddButtonViewHolder
    ) {
    }


    inner class AddButtonViewHolder(private val v: View) :
        RecyclerView.ViewHolder(v) {
        init {
            v.setOnClickListener(clickListener)
        }
    }
}