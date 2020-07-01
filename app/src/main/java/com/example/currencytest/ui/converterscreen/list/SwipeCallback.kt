package com.example.currencytest.ui.converterscreen.list

import android.icu.lang.UCharacter.IndicPositionalCategory.LEFT
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

interface RemoveCallback {
    fun removeItem(position: Int)
}

class SwipeCallback(private val removeCallback: RemoveCallback) :
    ItemTouchHelper.SimpleCallback(0, LEFT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun getSwipeDirs(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return if (viewHolder is AddButtonDelegateAdapter.AddButtonViewHolder) 0 else LEFT
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        //сомневаюсь вообще что так можно, но как иначе - не придумала
     removeCallback.removeItem(viewHolder.adapterPosition)
    }
}