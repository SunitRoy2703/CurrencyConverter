package com.sunit.currencyconverterapp.data

import com.sunit.currencyconverterapp.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CurrencyApi {
    @Headers("apikey: PuCy6V4q1tD5EQ83SeyF5UHbXYjCUtJ5")
    @GET("/exchangerates_data/latest")
    suspend fun getRates(
        @Query("base") base: String
    ): Response<CurrencyResponse>
}