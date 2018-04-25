package com.example.kotlin.home

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.huangyaping.yhwebtest.R
import com.example.kotlin.L
import com.example.kotlin.base.BaseLayout
import com.example.kotlin.base.BaseResult
import com.example.kotlin.home.adapter.HomeListAdapter
import com.example.kotlin.home.api.HomeApi
import com.example.kotlin.home.bean.HomeListData
import com.example.kotlin.net.BaseSubscriber
import com.example.kotlin.net.OKHttpManager
import com.lidroid.xutils.view.annotation.ViewInject
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

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
class HomePagerView(context: Context) : BaseLayout(context) {

    @ViewInject(R.id.recycler_home_view)
    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mHomeAdapter: HomeListAdapter

    override val layoutId: Int get() = R.layout.layout_home_view

    override fun initView() {

        mRecyclerView.layoutManager = LinearLayoutManager(context)

        val userList = ArrayList<String>()
        userList.add("测试数据")

        asList(1, 2, 3)
    }

    fun <T> asList(vararg a: T): ArrayList<T> {

        val array = ArrayList<T>()
        for (s in a) {
            array.add(s)
        }

        for (m in array) {
            Log.i("asList_0_", "asList_" + m)
        }

        array.forEach { array -> "${Log.i("asList_1_", "_array_" + array)}!" }


        var map = HashMap<String, String>()

        map.put("我是 K", "我是 V")

        for ((k, v) in map) {
            Log.i("asList_1_", " _k_" + k + "_v_" + v)
        }

        map.map { map -> "${Log.i("asList_2_", "" + map.key + "_v_" + map.value)}!" }

        map.mapValues { (k, v) ->
            Log.i("asList_3_", "" + k + "_v_" + v)
        }

        Log.i("getStrings_", "Any is String" + getStrings(1212))
        Log.i("getInt_", "Any is int" + getStrings("123456"))

        toListArray()

        var list = listOf<Int>(1, 2, 3)

        list.forEach { list -> testWhen(list) }

        checkUser()

        checkUserList()

        return array
    }

    fun checkUserList() {
        var mUserList = listOf<String>("1", "2", "3", "4")

        mUserList.forEach {

            item ->
            if ("1" in item) L.i("这是——" + item) else L.i("这是++" + item)

            if ("3" in item) return
        }
    }

    fun getInt(obj: Any): Int {

        if (obj is Int) {
            return obj
        }

        return -1
    }

    fun toListArray() {
        var list = listOf<String>("12", "13", "14", "15")

        for (item in list) {
            L.i("item_for_" + item)
        }

        list.forEach { L.i("-forEach-" + list) }


        var index: Int = 0
        while (index < list.size) {
            L.i("while_" + list.get(index))

            index++
        }
    }

    fun testWhen(index: Int) {

        when (index) {
            1 -> L.i("第一条数据" + index)
            2 -> L.i("第二条数据" + index)
            3 -> L.i("第三条数据" + index)

        }
    }

    fun checkUser() {
        var mList = listOf<String>("YHP", "LLJ", "SSK", "KKG")

        var index: Int = 0
        while (index < mList.size) {

            var s = mList.get(index)

            index++

            if ("YHP" in s) {
                L.i("checkUser_Y_" + s)
            } else if ("LLJ" in s) {
                L.i("checkUser_L_" + s)
            } else {
                L.i("checkUser_no_" + s)
            }

        }
    }

    fun getStrings(obj: Any): String {

        if (obj is String && obj.length != 0) {

            return obj.toString()
        }

        return ""
    }

    fun compare(b: Int, a: Int): Boolean = b > a

    override fun initData() {
        var mListData: ArrayList<HomeListData> = ArrayList()
        for (i in 0..20) {
            var homeListData = HomeListData()
            homeListData.msg = "测试数据" + i
            mListData?.add(homeListData)
        }
        mHomeAdapter = HomeListAdapter(context, mListData!!)
        mRecyclerView.adapter = mHomeAdapter


        requestData()
    }

    fun requestData() {
        OKHttpManager.getInstance()?.create(HomeApi::class.java)?.homeList()
                ?.subscribeOn(Schedulers.io())
                ?.unsubscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : BaseSubscriber<HomeListData>() {

                    override fun onFailure(code: Int, msg: String) {

                    }

                    override fun onSuccess(bean: BaseResult<HomeListData>?) {
                    }

                })
    }
}

