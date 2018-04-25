package com.example.kotlin.home.api

import com.example.kotlin.base.BaseResult
import com.example.kotlin.home.bean.HomeListData
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

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

interface HomeApi {


    @FormUrlEncoded
    @POST("/all/10/10")
    fun homeList(): Observable<BaseResult<HomeListData>>

}