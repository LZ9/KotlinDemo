package com.lodz.android.kotlindemo.bean

/**
 * 人
 * Created by zhouL on 2018/6/11.
 */
open class PeopleBean {

    var name = ""
    var age = 0

    constructor()

    constructor(name: String, age: Int) : this(){
        this.name = name
        this.age = age
    }

}