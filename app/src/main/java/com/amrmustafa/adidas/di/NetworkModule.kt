package com.amrmustafa.adidas.di
import com.amrmustafa.adidas.data.remote.api.ApiService
import com.amrmustafa.adidas.utils.Constants.base_url
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { provideStarWarsService(retrofit = get()) }

    single { provideRetrofit(okHttpClient = get(), url = base_url) }

    single { provideOkHttpClient() }
}

internal fun provideOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

internal fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

internal fun provideStarWarsService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)
