package com.example.apimethods10.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnection() {

    lateinit var retrofit: Retrofit
    val httpClient = OkHttpClient.Builder()
    val env = InitialConfig()

    private fun getHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                // .addHeader(key, token)
                .build()
            chain.proceed(request)
        }
    }

    fun getRetrofitIntance(): Retrofit {
        if (!::retrofit.isInitialized) {
            httpClient.addInterceptor(getHeaderInterceptor())
            httpClient.networkInterceptors()
            retrofit = Retrofit.Builder()
                .baseUrl(env.getEnv().urlbase)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return getRetrofitIntance().create(serviceClass)
    }
}

