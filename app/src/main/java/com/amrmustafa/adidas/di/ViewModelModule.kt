package com.amrmustafa.adidas.di

import com.amrmustafa.adidas.viewmodel.DetailViewModel
import com.amrmustafa.adidas.viewmodel.FavoritesViewModel
import com.amrmustafa.adidas.viewmodel.SearchViewModel
import com.amrmustafa.adidas.viewmodel.FavoriteDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel {
        SearchViewModel(
            searchProductsUseCase  = get(named("search"))
        )
    }

    viewModel {
        DetailViewModel(
            getReviewsUseCase = get(named("reviews")),
            postReviewsUseCase = get(named("postReviews")),
           )
    }

    viewModel {
        FavoritesViewModel(
            getAllFavoritesUseCase = get(named("get_all_favorites"))
        )
    }


    viewModel {
        FavoriteDetailViewModel(
            deleteFavoriteByIDUseCase = get(named("delete_favorite_by_name")),
            insertProductUseCase = get(named("insert_favorite")),
            insertReviewsUseCase = get(named("insert_reviews")),
            getProductByIDUseCase = get(named("get_favorite_by_name"))
        )
    }

}