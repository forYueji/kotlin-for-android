package com.example.kotlin.net

import com.example.kotlin.base.BaseResult
import rx.Subscriber

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
abstract class BaseSubscriber<T> : Subscriber<BaseResult<T>>() {

    private val ERROR_CODE = -1
    private val SUCCESS_CODE = 0

    /**
     * Provides the Observer with a new item to observe.
     *
     *
     * The [Observable] may call this method 0 or more times.
     *
     *
     * The `Observable` will not call this method again after it calls either [.onCompleted] or
     * [.onError].
     *
     * @param t
     * the item emitted by the Observable
     */
    override fun onNext(data: BaseResult<T>?) {

        if (null == data){
            onFailure(ERROR_CODE, "请求错误")
            return
        }
        onSuccess(data)
    }

    /**
     * Notifies the Observer that the [Observable] has finished sending push-based notifications.
     *
     *
     * The [Observable] will not call this method if it calls [.onError].
     */
    override fun onCompleted() {
    }

    /**
     * Notifies the Observer that the [Observable] has experienced an error condition.
     *
     *
     * If the [Observable] calls this method, it will not thereafter call [.onNext] or
     * [.onCompleted].
     *
     * @param e
     * the exception encountered by the Observable
     */
    override fun onError(e: Throwable?) {
    }

    abstract fun onSuccess(bean: BaseResult<T>?)

    abstract fun onFailure(code: Int, msg: String)
}