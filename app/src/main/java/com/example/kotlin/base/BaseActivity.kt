package com.example.kotlin.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import butterknife.ButterKnife
import butterknife.Unbinder
import com.lidroid.xutils.ViewUtils
import java.nio.Buffer

/**
 * Created by hyp on 2018/3/27.
 *
 *
 *
 * Title:
 *
 *
 *
 * Description:
 *
 *
 *
 * Copyright: Copyright (c) 2017
 *
 *
 *
 * Company:
 */

abstract class BaseActivity : AppCompatActivity() {

    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutId)

        ViewUtils.inject(this)

        init()

        initView()

        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun initView()

    abstract fun init()

    abstract fun initData()
}
