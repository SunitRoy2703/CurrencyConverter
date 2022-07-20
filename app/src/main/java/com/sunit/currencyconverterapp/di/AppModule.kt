package com.sunit.currencyconverterapp.di

import com.sunit.currencyconverterapp.data.CurrencyApi
import com.sunit.currencyconverterapp.main.DefaultMainRepository
import com.sunit.currencyconverterapp.main.MainRepository
import com.sunit.currencyconverterapp.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


private const val BASE_URL = "https://api.apilayer.com/exchangerates_data/"

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideOkHttp(): OkHttpClient {
//        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
//            .addInterceptor(Interceptor { chain ->
//                var request: Request = chain.request()
//                val builder: Request.Builder = request.newBuilder()
//                    .addHeader("apikey", "PuCy6V4q1tD5EQ83SeyF5UHbXYjCUtJ5")
//
//                request = builder.build()
//                return@Interceptor chain.proceed(request)
//            })
//
//        return builder.build()
//    }


    @Singleton
    @Provides
    fun provideCurrencyApi(): CurrencyApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyApi::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(api: CurrencyApi): MainRepository = DefaultMainRepository(api)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

}