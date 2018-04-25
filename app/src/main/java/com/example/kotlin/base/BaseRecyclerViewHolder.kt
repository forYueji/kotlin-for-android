package com.example.kotlin.base

import android.support.v7.widget.RecyclerView
import android.view.View
import com.lidroid.xutils.ViewUtils

/**
 * Created by hyp on 2018/3/28.
 * <p>
 * <p>Title: </p>
 * <p>
 * <p>Description: </p>
 * <p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>
 * <p>Company: </p>
 */
open class BaseRecyclerViewHolder : RecyclerView.ViewHolder {

    private lateinit var mView: View

    constructor(view: View) : super(view) {
        mView = view

        ViewUtils.inject(this, mView)

    }

}