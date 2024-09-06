package com.test.mobile.adapter

import android.content.Context
import android.content.Intent
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.test.mobile.extensions.convertDateTime
import com.test.mobile.extensions.loadImageUrl
import com.test.mobile.network.request.Articles
import com.test.mobile.databinding.ItemNewsBinding
import com.test.mobile.view.WebViewActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsAdapter
@Inject
constructor(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context: Context
    private val items: MutableList<Articles> = arrayListOf()
    private val selectedPositions: SparseBooleanArray = SparseBooleanArray() // 선택된 위치들을 저장할 SparseBooleanArray

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
            binding.image.loadImageUrl(item.urlToImage)
            binding.title.text = item.title
            binding.time.text = convertDateTime(item.publishedAt)

            if (selectedPositions.get(position, false)) {
                binding.title.setTextColor(getColor(context, android.R.color.holo_red_dark))
            } else {
                binding.title.setTextColor(getColor(context, android.R.color.darker_gray))
            }

            binding.root.setOnClickListener {
                // 선택된 위치를 SparseBooleanArray에 추가 또는 제거
                if (selectedPositions.get(position, false)) {
                    selectedPositions.delete(position)
                } else {
                    selectedPositions.put(position, true)
                }
                notifyItemChanged(position) // 클릭된 항목의 색상을 변경

                val intent = Intent(context, WebViewActivity::class.java).apply {
                    putExtra("url", item.url)
                }
                context.startActivity(intent)
            }
        }
    }
}