package com.example.core.base

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.example.core.base.BaseLayout

/**
 * Created by hyp on 2018/3/28.

 * Title:

 * Description:
 *
 * Copyright: Copyright (c) 2017

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
        val childAt = mList!!.get(position)
        container.addView(childAt)
        return childAt
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(mList!!.get(position))
    }
}
