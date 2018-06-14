package com.lodz.android.kotlindemo.bean

/**
 * 人
 * Created by zhouL on 2018/6/11.
 */
open class PeopleBean {

    var name = ""
    var age = 0

    constructor()

    constructor(name: String, age: Int) : this() {
        this.name = name
        this.age = age
    }

    open fun getFirstName(): String {// 可以被重写的方法需要加上open关键字
        if (name.length > 0) {
            return name.substring(0, 1)
        }
        return name
    }
}