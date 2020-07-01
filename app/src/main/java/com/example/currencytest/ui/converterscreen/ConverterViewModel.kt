package com.example.currencytest.ui.converterscreen

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.example.currencytest.CurrencyRepository
import com.example.currencytest.R
import com.example.currencytest.db.RateEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class ConverterViewModel(private val repo: CurrencyRepository) : ViewModel() {
    @ExperimentalCoroutinesApi
    private val listFlow = repo.getList().distinctUntilChanged()

    @ExperimentalCoroutinesApi
    val listData = listFlow.asLiveData()
    private val errorString_ = MutableLiveData<@StringRes Int>()
    val errorString: LiveData<Int> = errorString_

    init {
        viewModelScope.launch {
            try {
                val ticker = ticker(5000, 0, coroutineContext)
                for (event in ticker) {
                    repo.updateList()
                }
            } catch (e: Exception) {
                Log.e("app", e.message)
                if (e is UnknownHostException)
                    errorString_.postValue(R.string.no_connection)
                else
                    errorString_.postValue(R.string.default_loading_error)
            }
        }
    }

    fun onItemRemoved(content: RateEntity?) {
        viewModelScope.launch {
            try {
                content?.let { repo.deletePair(it) }
            } catch (e: java.lang.Exception) {
                Log.e("app", "deleting  failed ",e)
                errorString_.postValue(R.string.delete_error)
            }
        }
    }
}