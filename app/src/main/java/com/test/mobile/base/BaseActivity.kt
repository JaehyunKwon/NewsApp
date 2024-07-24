package com.remember.mobile.test.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<T: ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: T

    /**
     * setContentView로 호출할 Layout의 리소스 Id.
     * ex) R.layout.activity_sbs_main
     */
    abstract val layoutId: Int

    /**
     * viewModel 로 쓰일 변수.
     */
    abstract val viewModel: ViewModel

    /**
     * 레이아웃을 띄운 직후 호출.
     * 뷰나 액티비티의 속성 등을 초기화.
     * ex) 리사이클러뷰, 툴바, 드로어뷰..
     */
    abstract fun initView()

    /**
     * 두번째로 호출.
     * 데이터 바인딩 및 rxjava 설정.
     * ex)  observe, databinding observe..
     */
    abstract fun initDataBinding()

    abstract fun onEvent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 초기화된 layoutResId로 databinding 객체 생성
        binding = DataBindingUtil.setContentView(this, layoutId)
        // live data를 사용하기 위해 해줘야함
        binding.lifecycleOwner = this@BaseActivity

        initView()
        onEvent()
        initDataBinding()
    }
}