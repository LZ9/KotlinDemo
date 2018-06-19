package com.lodz.android.kotlindemo.sp

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 泛型preference委托
 * Created by zhouL on 2018/6/19.
 */
class Preference<T>(val context: Context, val name: String, val default: T) : ReadWriteProperty<Any?, T> {

    val prefs by lazy {
        context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    @Suppress("UNCHECKED_CAST")
    private fun findPreference(name: String, default: T): T {
        val result: Any = when (default) {
            is Long -> prefs.getLong(name, default)
            is String -> prefs.getString(name, default)
            is Int -> prefs.getInt(name, default)
            is Boolean -> prefs.getBoolean(name, default)
            is Float -> prefs.getFloat(name, default)
            else -> throw IllegalArgumentException("This type can not be get into Preferences")
        }
        return result as T
    }


    private fun putPreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can not be put into Preferences")
        }.apply()
    }

}