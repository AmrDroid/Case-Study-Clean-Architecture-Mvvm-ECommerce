package com.amrmustafa.adidas.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.amrmustafa.adidas.R
import com.amrmustafa.adidas.databinding.ActivityHomeBinding
import com.amrmustafa.adidas.adapters.SearchResultAdapter
import com.amrmustafa.adidas.models.ProductPresentation
import com.amrmustafa.adidas.models.states.SearchViewState
import com.amrmustafa.adidas.utils.*
import com.amrmustafa.adidas.utils.Constants
import com.amrmustafa.adidas.utils.startActivity
import com.amrmustafa.adidas.viewmodel.SearchViewModel
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class HomeActivity : BaseActivity() {

    private val searchViewModel by viewModel<SearchViewModel>()

    private lateinit var binding: ActivityHomeBinding

    private val searchResultAdapter = SearchResultAdapter {
        startActivity<ProductDetailsActivity> {
            putExtra(Constants.PRODUCT_KEY, it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        configSupportActionBar()
        observeSearchViewState()
        searchViewModel.executeProductsSearch("")

        handleTextChanges()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun configSupportActionBar() {
        setSupportActionBar(binding.searchToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun handleTextChanges() {
        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            text?.let { name ->
                if (name.length >= 2) {
                    binding.noResultTextView.hide()
                    searchViewModel.executeProductsSearch(name.toString())
                }
            }
        }
    }

    private fun observeSearchViewState() {
        searchViewModel.searchViewState.observe(this, Observer { state ->

            handleSearchLoading(state)

            state.searchResults?.let { searchResults ->
                if (searchResults.isEmpty()) {
                    handleNoSearchResults()
                    return@let
                }
                handleSearchResults(searchResults)
            }

            handleSearchError(state)

        })
    }

    private fun handleSearchLoading(state: SearchViewState) {
        if (state.isLoading) {
            binding.searchResultsRecyclerView.hide()
            binding.searchResultsProgressBar.show()
        } else {
            binding.searchResultsProgressBar.hide()
            binding.searchResultsRecyclerView.show()
        }
    }


    private fun handleSearchResults(searchResults: List<ProductPresentation>) {
        binding.noResultTextView.hide()

        showSnackbar(
            binding.searchResultsRecyclerView,
            getString(R.string.info_search_done)
        )
        binding.searchResultsRecyclerView.apply {
            adapter = ScaleInAnimationAdapter(searchResultAdapter.apply {
                submitList(searchResults)
            })
            recyclerViewLineDecoration(this@HomeActivity)
        }
    }


    private fun handleNoSearchResults() {
        binding.searchResultsRecyclerView.hide()
        binding.noResultTextView.show()
        showSnackbar(
            binding.searchResultsRecyclerView,
            getString(R.string.info_no_results)
        )
    }

    private fun handleSearchError(state: SearchViewState) {
        state.error?.run {
            binding.noResultTextView.show()
            binding.searchResultsRecyclerView.remove()
            showSnackbar(
                binding.searchResultsRecyclerView,
                getString(this.message),
                isError = true
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_fav) {
            startActivity<FavoritesActivity>()
            true
        } else super.onOptionsItemSelected(item)
    }

}
