package com.example.kotlin

import android.app.Application
import com.example.kotlin.net.OKHttpManager
import com.squareup.picasso.Picasso

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
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initOKHttp()
    }

    private fun initOKHttp() {
        OKHttpManager.getInstance()?.initOKHttp()
    }
}