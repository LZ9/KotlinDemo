package com.lodz.android.kotlindemo.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 扩展委托
 * Created by zhouL on 2018/6/12.
 */
object DelegatesExt {

    /** 只能赋值一次的非空委托 */
    fun <T> notNullSingleValue(): ReadWriteProperty<Any?, T> = NotNullSingleValueVar()

    //实现 ReadOnlyProperty 和 ReadWriteProperty 具体取决于我们被委托的对象是 val 还是 var
    private class NotNullSingleValueVar<T> : ReadWriteProperty<Any?, T> {

        private var value: T? = null

        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return value ?: throw IllegalStateException("value not initialized")
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            if (this.value == null) {
                this.value = value
            } else {
                throw IllegalStateException("value already initialized ")
            }
        }
    }
}