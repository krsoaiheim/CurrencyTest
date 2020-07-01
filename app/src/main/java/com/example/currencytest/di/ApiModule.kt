package com.example.currencytest.di

import com.example.currencytest.Api
import com.example.currencytest.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule() {

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .build()
            .newBuilder()
            .apply {
                if (BuildConfig.DEBUG) {
                    val loggingInterceptor = HttpLoggingInterceptor()
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(loggingInterceptor)
                }
            }
        return client.build()
    }

    @Singleton
    @Provides
    fun provideApi(client: OkHttpClient): Api {
        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("https://europe-west1-revolut-230009.cloudfunctions.net")
            .build()
        return retrofit.create(Api::class.java)
    }
}