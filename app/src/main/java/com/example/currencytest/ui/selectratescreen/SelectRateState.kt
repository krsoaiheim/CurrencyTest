package com.example.currencytest.ui.selectratescreen

import com.example.currencytest.db.ConvertCurrency

sealed class SelectRateState
class FirstCurrencySucceedState(val baseCurrency:ConvertCurrency):SelectRateState()
class SecondCurrencySucceedState(val secondCurrency:ConvertCurrency):SelectRateState()
class AddErrorState:SelectRateState()