package com.test.mobile.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T: ViewBinding> : AppCompatActivity() {
    private var _binding: T? = null
    protected val binding: T
        get() = _binding!!

    abstract val bindingInflater: (layoutInflater: android.view.LayoutInflater) -> T

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
     * LiveData observe 설정
     */
    abstract fun initObserving()

    abstract fun onEvent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)

        initView()
        onEvent()
        initObserving()
    }
}