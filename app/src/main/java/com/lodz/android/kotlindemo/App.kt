package com.lodz.android.kotlindemo

import android.app.Application
import kotlin.properties.Delegates

/**
 * Application
 * Created by zhouL on 2018/6/12.
 */
class App : Application() {

    companion object {
        private var instance: App by Delegates.notNull()
        fun get() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}