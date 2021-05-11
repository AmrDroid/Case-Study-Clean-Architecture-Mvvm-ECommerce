package com.amrmustafa.adidas.di

import com.amrmustafa.adidas.domain.models.*
import com.amrmustafa.adidas.domain.repository.IDetailsRepository
import com.amrmustafa.adidas.domain.repository.ISearchRepository
import com.amrmustafa.adidas.domain.repository.IFavRepository
import com.amrmustafa.adidas.domain.usecases.*
import kotlinx.coroutines.flow.Flow
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.amrmustafa.adidas.domain.models.Result


val useCasesModule = module {

    single(named("search")) { provideSearchUseCase(get()) }

    single(named("reviews")) { provideReviewsUseCase(get()) }

    single(named("postReviews")) { providePostReviewUseCase(get()) }

    single(named("get_all_favorites")) { provideGetAllFavoritesUseCase(get()) }


    single(named("delete_favorite_by_name")) { provideDeleteFavoriteByNameUseCase(get()) }

    single(named("get_favorite_by_name")) { provideGetFavoriteByNameUseCase(get()) }


    single(named("insert_favorite")) { provideInsertFavProductUseCase(get()) }

    single(named("insert_reviews")) { provideInsertFavReviewsUseCase(get()) }


}

fun provideSearchUseCase(searchRepository: ISearchRepository): MainUseCase<String, Flow<List<Product>>> {
    return SearchUseCase(searchRepository)
}

fun provideReviewsUseCase(detailsRepository: IDetailsRepository): MainUseCase<String, Flow<List<Review>>> {
    return GetReviewsUseCase(detailsRepository)
}
fun providePostReviewUseCase(detailsRepository: IDetailsRepository): MainUseCase<Review, Flow<Review>> {
    return PostReviewUseCase(detailsRepository)
}



fun provideDeleteFavoriteByNameUseCase(favRepository: IFavRepository): MainUseCase<String, Flow<Int>> {
    return DeleteFavoriteByIDUseCase(favRepository)
}

fun provideGetAllFavoritesUseCase(favRepository: IFavRepository): MainUseCase<Unit, Flow<List<Product>>> {
    return GetAllFavoritesUseCase(favRepository)
}

fun provideGetFavoriteByNameUseCase(favRepository: IFavRepository): MainUseCase<String, Flow<Product?>> {
    return GetProductByIDUseCase(favRepository)
}

fun provideInsertFavProductUseCase(favRepository: IFavRepository): MainUseCase<Product, Flow<Long>> {
    return InsertFavProductUseCase(favRepository)
}

fun provideInsertFavReviewsUseCase(favRepository: IFavRepository): MainUseCase<List<Review>, Flow<Result>> {
    return InsertFavReviewsUseCase(favRepository)
}

