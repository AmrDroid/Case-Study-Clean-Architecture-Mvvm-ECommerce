package com.amrmustafa.adidas.di

import com.amrmustafa.adidas.data.remote.repository.DetailsRepository
import com.amrmustafa.adidas.data.remote.repository.SearchRepository
import com.amrmustafa.adidas.domain.repository.IDetailsRepository
import com.amrmustafa.adidas.domain.repository.ISearchRepository
import org.koin.dsl.module


val remoteDataSourceModule = module {

    single<ISearchRepository> { SearchRepository(apiService = get()) }

    single<IDetailsRepository> { DetailsRepository(apiService = get()) }

}