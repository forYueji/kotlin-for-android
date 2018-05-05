package com.example.core.base

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.core.base.view.HeaderView
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
abstract class BaseLayout(mContext: Context?) : FrameLayout(mContext) {

    /**
     * 当前 上下文
     */
    protected var mContext: Context? = null

    /**
     * contentView
     */
    private var mContentView: View? = null

    private var mHeaderView: HeaderView? = null

    /**
     * 布局填充器
     */
    private var mInflater: LayoutInflater? = null

    /**
     * toast view
     */
    private var mToastView: ToastViewLayout? = null

    /**
     * 这里初始化
     */
    init {
        if (mContext != null) {
            initLayout(mContext)
        }
    }

    fun initLayout(context: Context) {
        mContext = context
        mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?

        initContentView()

        initView()

        initData()

        invalidate()
    }

    /**
     * 获取 layoutId
     */
    abstract val layoutId: Int

    /**
     * 外部调用
     */
    abstract fun initView()

    /**
     * 外部调用 处理数据
     */
    abstract fun initData()

    private fun initContentView() {
        /**
         * 当前父容器
         */
        var mFatherFrameLayout = FrameLayout(mContext)

        var mContentLayout = LinearLayout(mContext)
        mContentLayout.orientation = LinearLayout.VERTICAL

        /**
         * content params
         */
        var mContentLayoutParams: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        var mHeaderLayoutParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, dip2px(mContext!!, 44f)
        )

        /**
         * Toast params
         */
        var mToastLayoutParams: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        mHeaderView = HeaderView(mContext!!)

        mContentView = mInflater!!.inflate(layoutId, this, false)

        mContentLayout.addView(mHeaderView, mHeaderLayoutParams)
        mContentLayout.addView(mContentView, mContentLayoutParams)

        /**
         * add content view
         */
        ViewUtils.inject(this, mContentView)

        mFatherFrameLayout.addView(mContentLayout, mContentLayoutParams)

        /**
         * add toast view
         */
        mToastView = ToastViewLayout(mContext)
        ViewUtils.inject(this, mToastView)
        mFatherFrameLayout.addView(mToastView, mToastLayoutParams)

        addView(mFatherFrameLayout)

    }

    fun setTitle(msg: String) {
        mHeaderView!!.setTitle(msg)
    }

    /**
     * Toast view
     */
    class ToastViewLayout(context: Context?) : FrameLayout(context), Handler.Callback {

        private val SHOW_TOAST_FLAG: Int = 1
        private val CANCEL_TOAST_FLAG: Int = 2

        override fun handleMessage(msg: Message?): Boolean {

            var action = msg?.what
            var message: String = msg!!.obj as String

            when (action) {
                SHOW_TOAST_FLAG -> showToast(message)

                CANCEL_TOAST_FLAG -> cancelToast()
            }
            return false
        }

        private var mHandler: Handler? = null

        init {
            if (null != context) {
                mHandler = Handler(Looper.myLooper(), this)
            }
        }

        fun showToast(msg: String) {
            // 这里对 toast 进行显示

            // 当前正在显示, 这里仅改变显示内容
            visibility = if (isShown) View.INVISIBLE else View.VISIBLE
        }

        fun cancelToast() {
            visibility = View.INVISIBLE
        }
    }

    /**
     * 这里对 Toast view 进行显示
     */
    fun showToast(msg: String) {
        mToastView!!.showToast(msg)
    }

    /**
     * 这里对 Toast view 进行关闭
     */
    fun cancelToast() {
        mToastView!!.cancelToast()
    }

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}