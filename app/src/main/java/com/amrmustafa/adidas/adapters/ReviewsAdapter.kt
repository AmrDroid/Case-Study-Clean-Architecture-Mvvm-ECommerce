package com.amrmustafa.adidas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amrmustafa.adidas.databinding.ItemReviewBinding
import com.amrmustafa.adidas.models.ReviewPresentation

internal class ReviewsAdapter : ListAdapter<ReviewPresentation, ReviewsAdapter.ViewHolder>(ReviewDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReviewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    internal class ViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(review: ReviewPresentation) {
            binding.review = review
            binding.executePendingBindings()


        }
    }

    companion object ReviewDiffer : DiffUtil.ItemCallback<ReviewPresentation>() {
        override fun areItemsTheSame(
            oldItem: ReviewPresentation,
            newItem: ReviewPresentation
        ): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(
            oldItem: ReviewPresentation,
            newItem: ReviewPresentation
        ): Boolean = oldItem == newItem
    }
}