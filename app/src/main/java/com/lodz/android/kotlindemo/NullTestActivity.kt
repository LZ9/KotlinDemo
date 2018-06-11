package com.lodz.android.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lodz.android.core.log.PrintLog

/**
 * 空测试
 * Created by zhouL on 2018/6/8.
 */
class NullTestActivity : AppCompatActivity() {

    private var b: String? = "abc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b = null
        PrintLog.d("testtag", b ?: "")
        PrintLog.i("testtag", b?.length.toString())
        PrintLog.e("testtag", (b?.length ?: -1).toString())
    }

}