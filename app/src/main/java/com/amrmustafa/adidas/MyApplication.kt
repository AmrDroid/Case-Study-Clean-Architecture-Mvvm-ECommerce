package com.amrmustafa.adidas
import android.app.Application

import com.amrmustafa.adidas.di.networkModule
import com.amrmustafa.adidas.di.viewModelsModule
import com.amrmustafa.adidas.di.localDataSourceModule
import com.amrmustafa.adidas.di.remoteDataSourceModule
import com.amrmustafa.adidas.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                networkModule,
                viewModelsModule,
                localDataSourceModule,
                remoteDataSourceModule,
                useCasesModule
            )
        }

    }

}