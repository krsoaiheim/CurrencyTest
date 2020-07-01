package com.example.currencytest.di

import android.content.Context
import androidx.room.Room
import com.example.currencytest.Api
import com.example.currencytest.CurrencyRepository
import com.example.currencytest.db.AppDatabase
import com.example.currencytest.db.RateDAO
import com.example.currencytest.ui.converterscreen.ConverterViewModelFactory
import com.example.currencytest.ui.selectratescreen.SelectRateViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class MainModule {
    @Singleton
    @Provides
    fun provideDAO(context: Context): RateDAO {
        return Room.databaseBuilder(context, AppDatabase::class.java, "rates_db").build()
            .getDao()
    }

    @Singleton
    @Provides
    fun provideRepository(api: Api, dao: RateDAO): CurrencyRepository =
        CurrencyRepository(api, dao)

    @Provides
    fun provideSelectRateVMFactory(repo: CurrencyRepository): SelectRateViewModelFactory {
        return SelectRateViewModelFactory(repo)
    }

    @Provides
    fun provideConvertRateVMFactory(repo: CurrencyRepository): ConverterViewModelFactory {
        return ConverterViewModelFactory(repo)
    }

}