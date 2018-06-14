package com.lodz.android.kotlindemo.bean

/**
 * 内部类测试
 * Created by zhouL on 2018/6/14.
 */
class Outer {

    private val id: Int = 12312
    val value = "成员属性"

    /**嵌套内部类**/
    inner class Inner {

        fun getId() = id  // 访问外部类成员

        fun getValue() = this@Outer.value
    }
}