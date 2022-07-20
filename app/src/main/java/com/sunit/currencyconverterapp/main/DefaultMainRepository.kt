package com.sunit.currencyconverterapp.main

import com.sunit.currencyconverterapp.data.CurrencyApi
import com.sunit.currencyconverterapp.data.models.CurrencyResponse
import com.sunit.currencyconverterapp.util.Resource
import javax.inject.Inject


class DefaultMainRepository @Inject constructor(
    private val api : CurrencyApi
) : MainRepository {

    override suspend fun getRates(headers: Map<String, String>, base: String): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates(base)
            val result = response.body()
            print(result.toString())
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch(e: Exception) {
            Resource.Error(e.message ?: "An error occured")
        }
    }

}