package com.example.currencytest

import android.app.Application
import com.example.currencytest.di.DaggerAppComponent

class App:Application() {
    val appComponent = DaggerAppComponent.factory().create(this)
    override fun onCreate() {
        super.onCreate()
            appComponent.inject(this)
    }
}