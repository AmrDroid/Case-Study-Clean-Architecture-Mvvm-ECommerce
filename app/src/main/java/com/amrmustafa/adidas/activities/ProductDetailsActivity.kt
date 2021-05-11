package com.amrmustafa.adidas.activities

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.amrmustafa.adidas.R
import com.amrmustafa.adidas.adapters.ReviewsAdapter
import com.amrmustafa.adidas.models.*
import com.amrmustafa.adidas.databinding.ActivityDetailBinding
import com.amrmustafa.adidas.utils.*
import com.amrmustafa.adidas.utils.Constants
import com.amrmustafa.adidas.utils.showSnackbar
import com.amrmustafa.adidas.viewmodel.FavoriteDetailViewModel
import com.amrmustafa.adidas.viewmodel.DetailViewModel
import com.bumptech.glide.Glide

internal class ProductDetailsActivity : BaseActivity() {

    private val detailViewModel by viewModel<DetailViewModel>()

    private val favoritesViewModel by viewModel<FavoriteDetailViewModel>()

    private lateinit var binding: ActivityDetailBinding


    private val reviewsAdpater = ReviewsAdapter()

    private var isFavorite = false

    lateinit var product: ProductPresentation

    lateinit var reviewPresentation: List<ReviewPresentation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        setSupportActionBar(binding.detailsToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        product =
            intent.getParcelableExtra(Constants.PRODUCT_KEY)!!

        if (product == null) {
            detailViewModel
                .displayError(R.string.error_loading_details)
        } else {
            detailViewModel.initView(product)

            checkIfFavorite()

            Glide.with(this).load(product.imgUrl).into(binding.image)

            invalidateOptionsMenu()

            observeNetworkChanges(product.product_id)

            observeFavoritePresentationCreationFromRemote()

        }
        observeFavoriteViewState()

        observeDetailViewState()

        observepostReviewViewState()

        binding.reviewsLayout.addReview.setOnClickListener {
            showAlertDialogButtonClicked()
        }
    }

    private fun observeDetailViewState() {
        detailViewModel.detailViewState.observe(this, Observer {
            bindBasicInfo(it.info)

            bindReviews(it.reviews)

            it.error?.let { e ->
                onError(
                    resources.getString(e.message),
                    it.reviews.isNullOrEmpty(),
                )
            }

            if (it.isComplete) {
                showSnackbar(
                    binding.reviewsLayout.addReview,
                    getString(R.string.info_loading_complete)
                )
                detailViewModel.createFavoritePresentationFromRemote()
            }
        })
    }


    private fun observepostReviewViewState() {
        detailViewModel.postReviewViewState.observe(this, Observer {
            binding.reviewsLayout.reviewsProgressBar.hide()

            it.error?.let { e ->
                onError(
                    resources.getString(e.message),
                    it.isComplete
                )
            }

            if (it.isComplete) {
                showSnackbar(
                    binding.reviewsLayout.addReview,
                    getString(R.string.info_loading_complete)
                )
                detailViewModel.createFavoritePresentationFromRemote()
            }
        })
    }

    private fun observeFavoritePresentationCreationFromRemote() {
        detailViewModel.remoteToFavoritePresentation
            .observe(this, Observer { presentation ->
                reviewPresentation = presentation
            })
    }

    private fun onError(
        message: String,
        reviewsStatus: Boolean,
    ) {
        if (reviewsStatus) {
            binding.reviewsLayout.reviewsProgressBar.hide()
            binding.reviewsLayout.reviewsErrorTextView.show()
        }
        showSnackbar(binding.reviewsLayout.addReview, message, isError = true)
    }

    private fun onErrorResolved() {
        binding.reviewsLayout.reviewsErrorTextView.remove()
        binding.reviewsLayout.reviewsProgressBar.show()
    }

    private fun observeNetworkChanges(productID: String) {
        onNetworkChange { isConnected ->
            if (isConnected) {
                onErrorResolved()
                detailViewModel.getReviewsDetails(productID, isRetry = true)
            }
        }
    }


    fun bindBasicInfo(product: ProductPresentation?) {
        supportActionBar?.title = product?.name ?: ""
        binding.product = product
    }


    fun bindReviews(reviews: List<ReviewPresentation>?) {
        reviews?.let {
            with(binding.reviewsLayout) {
                reviewsProgressBar.remove()
                if (reviews.isNotEmpty()) {
                    ratingList.apply {
                        adapter = reviewsAdpater.apply { submitList(reviews) }
                    }
                } else noReviewsTextView.show()
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorites_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val menuItem = menu?.getItem(0)
        if (isFavorite)
            menuItem?.setIcon(R.drawable.ic_favs_24dp)
        else
            menuItem?.setIcon(R.drawable.ic_no_favs_24dp)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (isFavorite) {
            removeFromFavorites()
            isFavorite = !isFavorite
        } else {
            product?.let { favorite ->
                addToFavorites(favorite)
                isFavorite = !isFavorite
            }
        }
        invalidateOptionsMenu()

        return true
    }

    fun checkIfFavorite() {
        favoritesViewModel.getFavorite(product.product_id)
    }

    private fun addToFavorites(product: ProductPresentation) {
        favoritesViewModel.saveProduct(product)
        detailViewModel.detailViewState.value?.reviews?.let { favoritesViewModel.saveReviews(it) }
        showSnackbar(
            binding.reviewsLayout.addReview,
            getString(R.string.info_added_to_favs)
        )
    }

    private fun removeFromFavorites() {
        favoritesViewModel.deleteFavorite(product.product_id)
        showSnackbar(
            binding.reviewsLayout.addReview,
            getString(R.string.info_removed_from_favs)
        )
    }

    private fun observeFavoriteViewState() {
        favoritesViewModel.favoriteViewState.observe(this, Observer {
            isFavorite = it.isFavorite
            invalidateOptionsMenu()
            it.error?.let { e ->
                showSnackbar(binding.reviewsLayout.addReview, getString(e.message))
            }
        })
    }

    fun showAlertDialogButtonClicked() {
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.rating_dialog, null)
        val btDone: Button = view.findViewById(R.id.confirmbtn)
        val cancel: Button = view.findViewById(R.id.cancel)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)
        val desc: EditText = view.findViewById(R.id.description)
        btDone.setOnClickListener {
            onNetworkChange { isConnected ->
                if (isConnected) {
                    onErrorResolved()
                    detailViewModel.postReviewDetails(
                        desc.text.toString(),
                        ratingBar.numStars,
                        isRetry = true
                    )
                }
            }
            dialog?.dismiss()
            binding.reviewsLayout.reviewsProgressBar.show()
            onNetworkChange { isConnected ->
                if (isConnected) {
                    onErrorResolved()
                    detailViewModel.getReviewsDetails(product.product_id, isRetry = true)
                }
            }
            reviewsAdpater.notifyDataSetChanged()

        }
        cancel.setOnClickListener {
            dialog?.dismiss()
        }
        builder.setView(view)
        dialog = builder.create()
        dialog.show()
    }
}
