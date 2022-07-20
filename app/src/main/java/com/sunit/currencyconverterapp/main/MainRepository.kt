package com.sunit.currencyconverterapp.main

import com.sunit.currencyconverterapp.data.models.CurrencyResponse
import com.sunit.currencyconverterapp.util.Resource
import retrofit2.http.Headers

interface MainRepository  {

    suspend fun getRates( headers: Map<String, String>, base: String): Resource<CurrencyResponse>

}