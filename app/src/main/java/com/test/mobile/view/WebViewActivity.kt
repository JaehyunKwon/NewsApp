package com.test.mobile.view

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.util.Log
import android.view.LayoutInflater
import android.webkit.*
import androidx.activity.viewModels
import com.test.mobile.base.BaseActivity
import com.test.mobile.R
import com.test.mobile.databinding.ActivityWebViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity : BaseActivity<ActivityWebViewBinding>() {

    override val bindingInflater: (layoutInflater: LayoutInflater) -> ActivityWebViewBinding
        get() = ActivityWebViewBinding::inflate

    override val layoutId: Int get() = R.layout.activity_web_view

    override val viewModel: MainViewModel by viewModels()

    override fun initView() {

        binding.webView.webChromeClient = WebChromeClient()
        binding.webView.webViewClient = NewsWebViewClient()

        val url = intent.getStringExtra("url")
        if (url != null) {
            binding.webView.loadUrl(url)
        }
    }

    override fun initObserving() {
    }

    override fun onEvent() {
    }

    inner class NewsWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            url.let {
                val uri = try {
                    Uri.parse(url)
                } catch (e: Exception) {
                    return false
                }

                return when (uri.scheme) {
                    "http", "https" -> {
                        startActivity(Intent(Intent.ACTION_VIEW, uri))
                        finish()
                        true
                    }
                    else -> {
                        view.loadUrl(url)
                        false
                    }
                }
            }
        }

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            Log.e("TAG", "onReceivedError : ${error?.description.toString()}\n")
        }

        override fun onReceivedHttpError(
            view: WebView?,
            request: WebResourceRequest?,
            errorResponse: WebResourceResponse?
        ) {
            super.onReceivedHttpError(view, request, errorResponse)
        }

        override fun onReceivedSslError(
            view: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) {
            super.onReceivedSslError(view, handler, error)
        }
    }
}