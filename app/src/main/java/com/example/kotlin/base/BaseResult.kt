package com.example.kotlin.base

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
class BaseResult<T> {
    var code: String? = ""

    var data: T? = null

    var message: String? = ""
}