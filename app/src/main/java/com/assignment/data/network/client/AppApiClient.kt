package com.assignment.data.network.client

import com.assignment.BuildConfig
import com.assignment.data.network.service.AppService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Help to create retorfit instance and http client
object AppApiClient {

    // help to create http client , can add log interceptor if we want to know logs of req and
    // response and also can create authentication interceptor here to pass header for token
    fun createHttpClient(): OkHttpClient {
        val requestInterceptor = Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .build()
            return@Interceptor chain.proceed(request)
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)

        return httpClient.build()
    }

    // used to create retrofit builder
    fun createAppService(
        client: OkHttpClient
    ): AppService {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppService::class.java)
    }
}