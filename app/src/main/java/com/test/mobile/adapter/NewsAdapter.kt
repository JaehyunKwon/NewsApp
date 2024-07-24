package com.test.mobile.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.remember.mobile.newsapp.databinding.ItemNewsBinding
import com.test.mobile.extensions.convertDateTime
import com.test.mobile.network.request.Articles
import com.test.mobile.view.WebViewActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsAdapter
@Inject
constructor(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context: Context
    private val items: MutableList<Articles> = arrayListOf()
    private var selectedPosition: Int? = null

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        context = parent.context
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items.let {
            (holder as NewsViewHolder).bind(it[position], position)
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
        fun bind(item: Articles, position: Int) {
            binding.imageUrl = item.urlToImage
            binding.title.text = item.title
            binding.time.text = convertDateTime(item.publishedAt)

            if (selectedPosition == position) {
                binding.title.setTextColor(getColor(context, android.R.color.holo_red_dark))
            } else {
                binding.title.setTextColor(getColor(context, android.R.color.darker_gray))
            }

            binding.root.setOnClickListener {
                selectedPosition = position
                notifyDataSetChanged()
                val intent = Intent(context, WebViewActivity::class.java).apply {
                    putExtra("url", item.url)
                }
                context.startActivity(intent)
            }

            binding.executePendingBindings()
        }
    }
}