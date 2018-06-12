package com.lodz.android.kotlindemo.bean

/**
 * 运动员数据
 * Created by zhouL on 2018/6/8.
 */
class PlayerBean(var id: Int, var club: String, name: String, age: Int) : PeopleBean(name, age) {

    fun getTitle(): String = name + "_" + club// 返回值可以直接使用"="来赋值
}
