package com.amrmustafa.casestudy.data.remote

import com.amrmustafa.adidas.data.remote.api.ApiService
import com.amrmustafa.casestudy.data.remote.helpers.ApiRequestDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


internal open class BaseClient {

    private lateinit var mockWebServer: MockWebServer

    lateinit var apiService: ApiService

    private lateinit var okHttpClient: OkHttpClient

    private lateinit var loggingInterceptor: HttpLoggingInterceptor

    @Before
    open fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = ApiRequestDispatcher()
        mockWebServer.start()
        loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        okHttpClient = okhttpClient(loggingInterceptor)

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    open fun shutDown() {
        mockWebServer.shutdown()
    }

    private fun okhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

}