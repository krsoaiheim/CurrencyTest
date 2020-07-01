package com.example.currencytest.ui.selectratescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencytest.CurrencyRepository

class SelectRateViewModelFactory(private val repository: CurrencyRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SelectRateViewModel(repository) as T
    }
}