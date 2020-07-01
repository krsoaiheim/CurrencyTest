package com.example.currencytest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.currencytest.ui.converterscreen.ConverterFragment
import com.example.currencytest.ui.selectratescreen.SelectRateFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as App).appComponent.inject(this)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.container,
                ConverterFragment()
            )
                .addToBackStack("convert").commit()
        }
    }

    fun addNewPair() {
        supportFragmentManager.beginTransaction().replace(R.id.container, SelectRateFragment())
            .addToBackStack("select").commit()
    }

    fun openConverter() {
        supportFragmentManager.popBackStack()
    }

}