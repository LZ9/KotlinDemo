package com.lodz.android.kotlindemo.bean

/**
 * 学生数据
 * Created by zhouL on 2018/6/13.
 */
class StudentBean : PeopleBean() {

    var grade = ""
        get() = "人民小学-" + field

    var studentNo: Int = 100
        set(value) {field = value + 1}

    var major: String = "数学"
        private set
}