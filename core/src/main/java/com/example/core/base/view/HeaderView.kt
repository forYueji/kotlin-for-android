package com.example.core.base.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.example.core.R

class HeaderView(context: Context) : FrameLayout(context) {

    init {
        initView()
    }

    private lateinit var mTitleView: TextView

    private fun initView() {
        var view = LayoutInflater.from(context).inflate(R.layout.layout_header_view, null)
        addView(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        mTitleView = findViewById(R.id.textview_header_view_title)

    }

    fun setTitle(msg: String): HeaderView {
        mTitleView!!.text = msg
        return this
    }

}