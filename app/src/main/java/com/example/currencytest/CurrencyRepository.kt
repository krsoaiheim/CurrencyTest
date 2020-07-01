package com.example.currencytest

import com.example.currencytest.db.ConvertCurrency
import com.example.currencytest.db.RateDAO
import com.example.currencytest.db.RateEntity
import kotlinx.coroutines.flow.first

class CurrencyRepository(private val api: Api, private val dao: RateDAO) {


    suspend fun addPair(
        baseCurrency: ConvertCurrency,
        secondCurrency: ConvertCurrency
    ) {
        val crString = baseCurrency.convertString(secondCurrency)
        val jsonObj = api.getLatestRates(listOf(crString))
        val value = jsonObj.body()?.get(crString)?.asFloat ?: 0F
        dao.insertRate(RateEntity(0, baseCurrency, secondCurrency, value))
    }

    suspend fun deletePair(ratePair: RateEntity) = dao.deleteRate(ratePair)
    suspend fun updateList() {
        val oldList = dao.getList().first()
        val jsonObj = api.getLatestRates(oldList.map { it.baseCurrency.convertString(it.second) })
        val newList = oldList.map { oldItem ->
            oldItem.convertValue =
                jsonObj.body()?.get(oldItem.baseCurrency.convertString(oldItem.second))?.asFloat
                    ?: 0F
            oldItem
        }
        dao.update(newList)

    }

    fun getList() = dao.getList()

    private fun ConvertCurrency.convertString(secondCurrency: ConvertCurrency): String =
        this.code.plus(secondCurrency.code)
}