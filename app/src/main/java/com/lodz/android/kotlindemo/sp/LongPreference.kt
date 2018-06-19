package com.lodz.android.kotlindemo.sp

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * sp委托
 * Created by zhouL on 2018/6/19.
 */
class StringPreference(val context: Context, val name: String, val default: String) : ReadWriteProperty<Any?, String> {

    val prefs by lazy {
        context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return prefs.getString(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        prefs.edit().putString(name, value).apply()
    }
}