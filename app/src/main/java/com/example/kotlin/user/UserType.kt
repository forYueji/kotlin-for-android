package com.example.kotlin.user

/**
 * Created by hyp on 2018/3/30.
 *
 *
 *
 * Title:
 *
 *
 *
 * Description:
 *
 *
 *
 * Copyright: Copyright (c) 2017
 *
 *
 *
 * Company:
 */

class UserType {


    private enum class API constructor(private val key: String) {

        HOME_LIST_API("homeList.api"),

        USER_INFO_API("userInfo.api"),

        CHANGE_USER_INFO_API("changeUserInfo.api");

        override fun toString(): String {
            return key
        }
    }
}
