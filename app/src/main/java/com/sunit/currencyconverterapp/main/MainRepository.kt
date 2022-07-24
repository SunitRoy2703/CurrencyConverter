package com.sunit.currencyconverterapp.main

import com.sunit.currencyconverterapp.data.models.CurrencyResponse
import com.sunit.currencyconverterapp.util.Resource

interface MainRepository  {

    suspend fun getRates( headers: Map<String, String>, base: String): Resource<CurrencyResponse>

}