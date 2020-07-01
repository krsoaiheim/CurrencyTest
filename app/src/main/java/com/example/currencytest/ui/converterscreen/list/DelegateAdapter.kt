package com.example.currencytest.ui.converterscreen.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class DelegateAdapter<M, in VH : RecyclerView.ViewHolder>(val modelClass: Class<out M>) {

    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun bindViewHolder(
        model: M,
        viewHolder: VH
    )
}
internal class DelegateAdapterItemDiffCallback : DiffUtil.ItemCallback<DelegateAdapterItem>() {

    override fun areItemsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean =
        oldItem::class == newItem::class && oldItem.id() == newItem.id()

    override fun areContentsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean =
        oldItem.content() == newItem.content()

}