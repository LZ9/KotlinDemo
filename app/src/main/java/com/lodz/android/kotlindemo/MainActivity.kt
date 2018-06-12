package com.lodz.android.kotlindemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.lodz.android.core.log.PrintLog
import com.lodz.android.kotlindemo.bean.PlayerBean
import org.jetbrains.anko.intentFor
import java.util.*

class MainActivity : AppCompatActivity() {

    private val ITEMS = arrayListOf(//list的常量赋值
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

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

        val list = ArrayList<PlayerBean>()// Kotlin实例化一个对象时不需要new关键字
        list.add(PlayerBean(1, "MUN", "Jack", 27))
        list.add(PlayerBean(2, "MUN", "Mick", 19))
        for (bean in list) {
            PrintLog.d("testtag", bean.getTitle())
        }

        with(list[0]){
            PrintLog.e("testtag", "$name")//利用with简化代码
        }

        val peopleBean = list.get(0);
        PrintLog.i("testtag", peopleBean.name)

        mTextTv.text = "哈哈哈哈";
        club = "MUN"

        (findViewById(R.id.jump_btn) as Button).setOnClickListener {
            // 设置点击监听
            toast("是的", Toast.LENGTH_LONG)
            jump()
        }

        val itemList = ITEMS;
        itemList.add("Mon 7/15 - Sunny - 21/11")


        val index: Int = 7
        val indexDouble: Double = index.toDouble()//转型


        val map = LinkedHashMap<String, String>()
        map["name"] = "chaychan"
        map["age"] = "22 years old"
        map["hobby"] = "programming";
        for ((k, v) in map) {//遍历map
            PrintLog.i("testtag", "$k  :  $v")
        }


        val c: Char = 'c'
        val i: Int = c.toInt()


        val exampleStr = "Example"
        val array = exampleStr[2] // 这是一个字符'a'
        PrintLog.d("testtag", array.toString())
        for(char in exampleStr){// 迭代String
            PrintLog.d("testtag", char.toString())
        }


        val ankoBtn = findViewById(R.id.anko_btn) as Button
        ankoBtn.visibility = View.VISIBLE
        ankoBtn.setOnClickListener {
            startActivity(intentFor<AnkoActivity>("id" to 5))
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
