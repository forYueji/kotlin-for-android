package com.example.kotlin.net

import okhttp3.OkHttpClient
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit

/**
 * Created by hyp on 2018/4/4.
 * <p>
 * <p>Title: </p>
 * <p>
 * <p>Description: </p>
 * <p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>
 * <p>Company: </p>
 */
class OKHttpManager() {

    private var mOkHttpClient: OkHttpClient? = null

    private var mRetrofit: Retrofit? = null

    companion object {
        val instances by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OKHttpManager()
        }

        @Volatile
        var mInstance: OKHttpManager? = null

        fun getInstance(): OKHttpManager? {

            if (null == mInstance){
                synchronized(this){
                    if (null == mInstance){
                        mInstance = OKHttpManager()
                    }
                }
            }
            return mInstance
        }
    }


    /**
     * 初始化 OKHttp
     */
    fun initOKHttp() {
        if (null == mOkHttpClient) {
            mOkHttpClient = OkHttpClient().newBuilder().build();
        }

        if (null == mRetrofit) {
            mRetrofit = Retrofit.Builder()
                    .baseUrl("http://gank.io/api/data/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(mOkHttpClient)
                    .build()
        }
    }

    fun <T> create(obj: Class<T>): T {
        if (null == mRetrofit) {
            initOKHttp()
        }
        return mRetrofit!!.create(obj)
    }
}