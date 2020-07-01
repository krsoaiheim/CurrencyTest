package com.example.currencytest.ui.converterscreen.list

import com.example.currencytest.db.RateEntity

interface DelegateAdapterItem {
    fun id(): Any
    fun content(): Any
}

class AddButtonItem :
    DelegateAdapterItem {
    override fun id() = 0
    override fun content() = Unit
}

class RateItem(private val ratePair: RateEntity) :
    DelegateAdapterItem {
    override fun id() = ratePair.id
    override fun content() = ratePair
}
