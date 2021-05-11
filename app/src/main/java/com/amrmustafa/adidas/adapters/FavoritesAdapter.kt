package com.amrmustafa.adidas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amrmustafa.adidas.databinding.ItemFavoriteBinding
import com.amrmustafa.adidas.models.ProductPresentation

internal class FavoritesAdapter(private val onClick: (ProductPresentation) -> Unit) :
    ListAdapter<ProductPresentation, FavoritesAdapter.ViewHolder>(FavoritesDiff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoriteBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, onClick = onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    internal class ViewHolder(
        private val binding: ItemFavoriteBinding,
        private val onClick: (ProductPresentation) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductPresentation) {
            binding.product = product
            binding.executePendingBindings()
            binding.favCard.setOnClickListener {
                onClick(product)
            }
        }
    }

    companion object FavoritesDiff : DiffUtil.ItemCallback<ProductPresentation>() {
        override fun areItemsTheSame(
            old: ProductPresentation,
            new: ProductPresentation
        ): Boolean =
                    old.product_id == new.product_id

        override fun areContentsTheSame(
            oldItem: ProductPresentation,
            newItem: ProductPresentation
        ): Boolean = oldItem == newItem
    }
}