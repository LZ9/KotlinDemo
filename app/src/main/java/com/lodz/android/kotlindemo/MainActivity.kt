package com.lodz.android.kotlindemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.lodz.android.kotlindemo.bean.PlayerBean

class MainActivity : AppCompatActivity() {

    private var name: String = "my name" //变量name为String类型

    val TAG = "ClassName" //  用val定义常量（相当于final），同时可以省略String，Kt支持自动推断变量类型

    private val mTextTv: TextView by lazy {
        // val的懒加载写法
        findViewById(R.id.text_tv) as TextView
    }

    lateinit var club: String// var的懒加载写法，lateinit不支持int、boolean、long等这些会有默认值的原始类型

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = ArrayList<PlayerBean>()// Kotlin实例化一个对象时不需要new关键字
        list.add(PlayerBean(1, "MUN", "jack", 27))
        for (bean in list) {
            Log.d("testtag", bean.getTitle())
        }

        var peopleBean = list.get(0);
        Log.i("testtag", peopleBean.name)

        mTextTv.text = "哈哈哈哈";
        club = "MUN"

        (findViewById(R.id.jump_btn) as Button).setOnClickListener {
            // 设置点击监听
            toast("是的", Toast.LENGTH_LONG)
            jump()
        }

    }

    fun jump() { //参数id为Int类型
        val intent = Intent(MainActivity@ this, NullTestActivity::class.java) //需要用::来使用Java类，注意是::（两个）
        startActivity(intent)
    }

    fun getAddr(id: Int): String { //返回值为String类型
        return ""
    }

    fun toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    override fun onBackPressed() {
        toast("退出")
        super.onBackPressed()
    }
}
