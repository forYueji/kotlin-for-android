package com.example.kotlin.kotlin

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.kotlin.R
import com.example.core.base.BaseLayout
import com.example.kotlin.view.BaseWebView


/**
 * Created by hyp on 2018/3/28.
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

class WebViewKotlinView(context: Context) : BaseLayout(context) {

    lateinit var mWebView: BaseWebView

    lateinit var mEditTextView: EditText

    lateinit var mClickView: TextView

    lateinit var mProgressBar: ProgressBar

    override val layoutId: Int get() = R.layout.layout_web_view

    override fun initView() {


        mEditTextView = findViewById(R.id.edit_text_main_view)

        mWebView = findViewById(R.id.webview_main_view)

        mClickView = findViewById(R.id.text_view_main_click_view)

        mProgressBar = findViewById(R.id.progressbar_main_view)

        mClickView.setOnClickListener { loadWebView(mEditTextView!!.text.toString()) }
    }

    override fun initData() {

        loadWebView("https://trade.yhfund.com.cn/yhapp/new/web/query/entry.do")
    }

    private fun loadWebView(url: String) {
        if (TextUtils.isEmpty(url)) {
            Toast.makeText(mContext, "url 不能为空", Toast.LENGTH_LONG).show()
            return
        }
        mEditTextView.setText(url)

        mWebView.loadUrl(url)

        mWebView.setOnWebLoadingListener(object : BaseWebView.OnWebLoadingListener {
            override fun onProgressChange(newProgress: Int) {
                mProgressBar.visibility = View.VISIBLE
                mWebView.visibility = View.GONE
                mProgressBar.progress = newProgress
            }

            override fun onLoadingSuccess() {
                mProgressBar.visibility = View.GONE
                mWebView.visibility = View.VISIBLE
            }

            override fun onPageStarted() {
                mProgressBar.visibility = View.VISIBLE
                mWebView.visibility = View.GONE
            }

            override fun onReceivedError() {
                Toast.makeText(mContext, "加载失败", Toast.LENGTH_LONG).show()
            }
        })
    }

}
