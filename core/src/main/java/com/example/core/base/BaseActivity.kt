package com.example.core.base

import android.annotation.TargetApi
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import com.lidroid.xutils.ViewUtils
import com.readystatesoftware.systembartint.SystemBarTintManager

/**
 * 所有 Activity 的基类
 */
abstract class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTintManager()

        val contentFrameLayout = findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
        val parentView = contentFrameLayout.getChildAt(0)
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.fitsSystemWindows = true
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE or
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN or
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        setContentView(layoutId)

        ViewUtils.inject(this)

        initField()

        initView()

        initData()
    }

    /**
     * 初始化 状态栏 颜色
     */
    private fun initTintManager() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus()
        }

        val tintManager = SystemBarTintManager(this)
        tintManager.isStatusBarTintEnabled = true
        tintManager.setStatusBarTintResource(actionColor)//通知栏所需颜色
    }

    @TargetApi(19)
    private fun setTranslucentStatus() {
        val window = window
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

    abstract val actionColor: Int

    abstract val layoutId: Int

    /**
     * 处理参数
     */
    abstract fun initField()

    /**
     * 处理 view
     */
    abstract fun initView()

    /**
     * 处理数据
     */
    abstract fun initData()
}
