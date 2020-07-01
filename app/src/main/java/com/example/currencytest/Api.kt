package com.example.currencytest

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    @GET("/revolut-ios")
    suspend fun getLatestRates(@Query("pairs") pairs: List<String>): Response<JsonObject>
}