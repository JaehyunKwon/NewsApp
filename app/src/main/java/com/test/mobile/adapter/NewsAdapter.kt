package com.test.mobile.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.remember.mobile.newsapp.databinding.ItemNewsBinding
import com.test.mobile.network.request.Articles
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsAdapter
@Inject
constructor(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: MutableList<Articles> = arrayListOf()

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items.let {
            (holder as NewsViewHolder).bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(articles: List<Articles>) {
        items.clear()
        items.addAll(articles)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(
        private val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Articles) {
            binding.imageUrl = item.urlToImage
            binding.title.text = item.title
            binding.time.text = item.publishedAt

            binding.executePendingBindings()
        }
    }
}