package com.example.currencytest.ui.converterscreen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencytest.CurrencyRepository

class ConverterViewModelFactory(private val repository: CurrencyRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ConverterViewModel(repository) as T
    }
}