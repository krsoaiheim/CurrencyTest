package com.example.currencytest.di

import android.content.Context
import com.example.currencytest.App
import com.example.currencytest.MainActivity
import com.example.currencytest.ui.converterscreen.ConverterFragment
import com.example.currencytest.ui.selectratescreen.SelectRateFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApiModule::class, MainModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(app: App)
    fun inject(activity: MainActivity)
    fun inject(fragment: SelectRateFragment)
    fun inject(fragment: ConverterFragment)
}