package com.test.mobile.view

import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.test.mobile.base.BaseActivity
import com.test.mobile.adapter.NewsAdapter
import com.test.mobile.extensions.screenWidthDp
import com.test.mobile.R
import com.test.mobile.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override val layoutId: Int get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModels()

    private var lastTimeBackPressed: Long = 0
    private var isBack = false

    private lateinit var newsAdapter: NewsAdapter

    override fun initView() {
        newsAdapter = NewsAdapter()

        binding.recyclerview.apply {
            val spanCount = if (screenWidthDp(applicationContext) >= 600) 3 else 1
            layoutManager = GridLayoutManager(applicationContext, spanCount)
            adapter = newsAdapter
        }

        viewModel.getNews()
    }

    override fun initObserving() {
        viewModel.newsListLiveData.observe(this) { data ->
            newsAdapter.submitList(data)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.loadingIndicator.visibility = View.VISIBLE
            } else {
                binding.loadingIndicator.visibility = View.INVISIBLE
            }
        }
        viewModel.isError.observe(this) { isError ->
            if (isError) {
                binding.errorLayout.visibility = View.VISIBLE
                binding.recyclerview.visibility = View.INVISIBLE
            } else {
                binding.errorLayout.visibility = View.INVISIBLE
                binding.recyclerview.visibility = View.VISIBLE
            }
        }
    }

    override fun onEvent() {
        binding.btnRetry.setOnClickListener {
            viewModel.getNews()
        }
    }

    override fun onBackPressed() {
        isBack = true

        if (System.currentTimeMillis() - lastTimeBackPressed < 2000) {
            finish()
            return
        }
        lastTimeBackPressed = System.currentTimeMillis()
        Toast.makeText(this, getString(R.string.main_back_finished), Toast.LENGTH_SHORT).show()
    }
}