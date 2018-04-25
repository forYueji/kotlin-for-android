package com.example.kotlin.base

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

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

class BasePagerAdapter(private val mList: List<BaseLayout>?) : PagerAdapter() {

    override fun getCount(): Int {
        return mList?.size ?: 0
    }

    override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
        return arg0 === arg1
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val childAt = container.getChildAt(position)
        if (childAt == null) {
            val pager = mList!![position]
            val parent = pager.parent as ViewGroup
            parent?.removeAllViews()
            container.addView(pager)
        }
        return mList!![position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {}
}
