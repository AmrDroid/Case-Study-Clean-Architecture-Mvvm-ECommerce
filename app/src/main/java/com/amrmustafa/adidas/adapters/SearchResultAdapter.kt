package com.amrmustafa.adidas.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amrmustafa.adidas.databinding.ItemSearchBinding
import com.amrmustafa.adidas.models.ProductPresentation
import com.bumptech.glide.Glide

internal class SearchResultAdapter(private val onClick: (ProductPresentation) -> Unit) :
    ListAdapter<ProductPresentation, SearchResultAdapter.ViewHolder>(SearchResultDiff) {

    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(inflater, parent, false)
        context = parent.context

        return ViewHolder(binding, onClick = onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), context)
    }

    class ViewHolder(
        private val binding: ItemSearchBinding,
        private val onClick: (ProductPresentation) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductPresentation, context: Context) {
            binding.searchedProduct = product
            binding.executePendingBindings()


            binding.constraintlayout.setOnClickListener {
                onClick(product)
            }
            Glide.with(context).load(product.imgUrl).circleCrop().into(binding.imageView)

        }
    }

    companion object SearchResultDiff : DiffUtil.ItemCallback<ProductPresentation>() {
        override fun areItemsTheSame(
            oldItem: ProductPresentation,
            newItem: ProductPresentation
        ): Boolean =
            oldItem.product_id == newItem.product_id

        override fun areContentsTheSame(
            oldItem: ProductPresentation,
            newItem: ProductPresentation
        ): Boolean = oldItem == newItem
    }
}