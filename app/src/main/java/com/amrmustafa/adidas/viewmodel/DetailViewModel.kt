package com.amrmustafa.adidas.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amrmustafa.adidas.domain.models.Review
import com.amrmustafa.adidas.utils.ExceptionHandler
import com.amrmustafa.adidas.domain.usecases.MainUseCase
import com.amrmustafa.adidas.converters.toViewModel
import com.amrmustafa.adidas.models.ProductPresentation
import com.amrmustafa.adidas.models.ReviewPresentation
import com.amrmustafa.adidas.models.states.DetailsViewState
import com.amrmustafa.adidas.models.states.ErrorState
import com.amrmustafa.adidas.models.states.PostReviewViewState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

internal class DetailViewModel(
    private val getReviewsUseCase: MainUseCase<String, Flow<ArrayList<Review>>>,
    private val postReviewsUseCase: MainUseCase<Review, Flow<Review>>
) : BaseViewModel() {
    private var reviewsJob: Job? = null

    lateinit var product_ID: String

    private var postReviewJob: Job? = null

    val postReviewViewState: LiveData<PostReviewViewState>
        get() = _postReviewViewState

    private var _postReviewViewState = MutableLiveData<PostReviewViewState>()


    val detailViewState: LiveData<DetailsViewState>
        get() = _detailViewState

    private var _detailViewState = MutableLiveData<DetailsViewState>()

    val remoteToFavoritePresentation: LiveData<List<ReviewPresentation>>
        get() = _remoteToFavoritePresentation

    private var _remoteToFavoritePresentation = MutableLiveData<List<ReviewPresentation>>()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _detailViewState.value = _detailViewState.value?.copy(error = ErrorState(message))
        _postReviewViewState.value = _postReviewViewState.value?.copy(error = ErrorState(message))
    }

    init {
        _detailViewState.value =
            DetailsViewState(
                isComplete = false,
                error = null,
                reviews = null,
                info = null
            )
        _postReviewViewState.value =
            PostReviewViewState(
                isComplete = false,
                error = null,
                reviews = null,
            )
    }

    override fun onCleared() {
        super.onCleared()
        reviewsJob?.cancel()
        postReviewJob?.cancel()
    }

    fun initView(product: ProductPresentation) {
        _detailViewState.value = _detailViewState.value?.copy(info = product)
    }

    fun getReviewsDetails(productID: String, isRetry: Boolean = false) {
        if (isRetry) {
            _detailViewState.value = _detailViewState.value?.copy(error = null)
        }

        product_ID = productID
        reviewsJob = launchCoroutine {
            async { loadReviews(productID) }.await()
            _detailViewState.value = _detailViewState.value?.copy(isComplete = true)
        }
    }

    fun postReviewDetails(text: String, rating: Int, isRetry: Boolean = false): ReviewPresentation {
        if (isRetry) {
            _postReviewViewState.value = _postReviewViewState.value?.copy(error = null)
        }

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")

        Log.d("inside post review ", text)
        val review = Review(
            product_ID,
            text,
            rating.toString(),
            text,
            sdf.format(Date()),
            sdf.format(Date()),
        )
        postReviewJob = launchCoroutine {
            async {
                postReview(review)
            }.await()
            _postReviewViewState.value = _postReviewViewState.value?.copy(isComplete = true)
        }
       return review.toViewModel()
    }

    fun displayError(message: Int) {
        _detailViewState.value = _detailViewState.value?.copy(error = ErrorState(message))
    }


    private suspend fun loadReviews(productID: String) {
        getReviewsUseCase(productID).collect { products ->
            val productPresentation = products.map { film -> film.toViewModel() }
            _detailViewState.value = _detailViewState.value?.copy(reviews = productPresentation)
        }
    }

    private suspend fun postReview(review: Review) {

        postReviewsUseCase(review).collect { review ->
            _postReviewViewState.value =
                _postReviewViewState.value?.copy(reviews = review.toViewModel())
        }
    }

    fun createFavoritePresentationFromRemote() {
        val reviews = _detailViewState.value?.reviews ?: return
        _remoteToFavoritePresentation.value = reviews
    }
}
