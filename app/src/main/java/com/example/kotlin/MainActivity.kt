package com.example.kotlin

import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.core.base.BaseActivity
import com.example.core.base.BaseLayout
import com.example.huangyaping.yhwebtest.home.UserPagerView
import com.example.kotlin.home.DiscoverPagerView
import com.example.kotlin.home.HomePagerView
import com.example.kotlin.home.ListPagerView
import com.example.kotlin.home.PhegdaPagerView
import com.lidroid.xutils.view.annotation.ViewInject

class MainActivity : BaseActivity() {
    override val actionColor: Int
        get() = R.color.white

    /**
     * home 容器
     */
    @ViewInject(R.id.frame_layout)
    lateinit var mFrameLayout: FrameLayout

    /**
     * 首页
     */
    @ViewInject(R.id.foot_bar_home)
    lateinit var mHomeLayout: LinearLayout

    /**
     * 列表
     */
    @ViewInject(R.id.foot_bar_fund)
    lateinit var mListLayout: LinearLayout

    /**
     * 天玑
     */
    @ViewInject(R.id.main_footbar_phegda)
    lateinit var mPhegdaLayout: LinearLayout

    /**
     * 发现
     */
    @ViewInject(R.id.main_footbar_discover)
    lateinit var mDiscoverLayout: LinearLayout

    /**
     * 我的
     */
    @ViewInject(R.id.main_footbar_user)
    lateinit var mUserLayout: LinearLayout

    override val layoutId: Int get() = R.layout.activity_main

    /**
     * 参数
     */
    override fun initField() {

    }

    override fun initView() {
        var mHomePagerView = HomePagerView(this);
        var mListPagerView = ListPagerView(this);
        var mDiscoverPagerView = DiscoverPagerView(this);
        var mPhegdaPagerView = PhegdaPagerView(this);
        var mUserPagerView = UserPagerView(this);

        var mViewList = listOf(mHomePagerView, mListPagerView,
                mPhegdaPagerView, mDiscoverPagerView, mUserPagerView)
        initClickView(mViewList)

        createView(readeBaseLayout(0, mViewList))
    }

    override fun initData() {

    }

    private fun initClickView(mViewList: List<BaseLayout>) {
        mHomeLayout.setOnClickListener({ v -> createView(readeBaseLayout(0, mViewList)) })
        mListLayout.setOnClickListener({ v -> createView(readeBaseLayout(1, mViewList)) })
        mPhegdaLayout.setOnClickListener({ v -> createView(readeBaseLayout(2, mViewList)) })
        mDiscoverLayout.setOnClickListener({ v -> createView(readeBaseLayout(3, mViewList)) })
        mUserLayout.setOnClickListener({ v -> createView(readeBaseLayout(4, mViewList)) })
    }

    private fun readeBaseLayout(position: Int, mViewList: List<BaseLayout>): BaseLayout {
        return mViewList!!.get(position)
    }

    private fun createView(baseLayout: BaseLayout) {
        mFrameLayout.removeAllViews()
        mFrameLayout.addView(baseLayout)
    }
}
