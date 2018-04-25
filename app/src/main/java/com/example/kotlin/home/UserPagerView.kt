package com.example.huangyaping.yhwebtest.home

import android.content.Context
import com.example.huangyaping.yhwebtest.R
import com.example.kotlin.base.BaseLayout
import com.example.kotlin.user.UserProfile

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
class UserPagerView(context: Context) : BaseLayout(context) {

    override val layoutId: Int get() = R.layout.layout_user_view

    override fun initView() {

        UserProfile.mUserName
    }

    override fun initData() {
    }

}