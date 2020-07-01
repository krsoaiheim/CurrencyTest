package com.example.currencytest.ui.selectratescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencytest.CurrencyRepository
import com.example.currencytest.db.ConvertCurrency
import kotlinx.coroutines.launch

class SelectRateViewModel(private val repo: CurrencyRepository) : ViewModel() {

    private var firstCurrency: ConvertCurrency? = null
    private var secondCurrency: ConvertCurrency? = null
    private var array = ConvertCurrency.values().asList()
    private val listData_ = MutableLiveData(array)
    val listData: LiveData<List<ConvertCurrency>> = listData_
    val stepFinishEvent = MutableLiveData<SelectRateState>()

    fun onSearchEdited(search: String) {
        listData_.postValue(array.filter { it.code.contains(search, ignoreCase = true) })
    }

    fun onItemSelected(currency: ConvertCurrency) {
        if (firstCurrency == null) {
            firstCurrency = currency
            array = array.filter { it != currency }
            listData_.postValue(array)
            stepFinishEvent.postValue(FirstCurrencySucceedState(firstCurrency!!))
        } else {
            secondCurrency = currency
            viewModelScope.launch {
                try {
                    repo.addPair(firstCurrency!!, secondCurrency!!)
                    stepFinishEvent.postValue(SecondCurrencySucceedState(secondCurrency!!))
                } catch (e: Exception) {
                    stepFinishEvent.postValue(AddErrorState())
                    firstCurrency = null
                    secondCurrency = null
                    ConvertCurrency.values().asList()
                    listData_.postValue(array)
                }

            }
        }
    }

}