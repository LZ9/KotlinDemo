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
import com.lodz.android.kotlindemo.list.ListActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private val ITEMS = arrayListOf(//list的常量赋值
            "Mon",
            "Tue",
            "Wed",
            "Thurs",
            "Fri",
            "Sat",
            "Sun"
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
        for (bean in list) {//迭代对象
            PrintLog.d("testtag", bean.getTitle())
        }
        for (index in list.indices) {// 迭代索引
            PrintLog.d("testtag", index.toString())
        }

        for ((index, bean) in list.withIndex()) {//同时迭代索引和对象
            PrintLog.d("testtag", index.toString() + " ; " + bean.getTitle())
        }

        with(list[0]) {
            PrintLog.e("testtag", name)//利用with可以直接访问到对象里的public数据
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
        for (char in exampleStr) {// 迭代String
            PrintLog.d("testtag", char.toString())
        }

        val ankoBtn = findViewById(R.id.anko_btn) as Button
        ankoBtn.visibility = View.VISIBLE
        ankoBtn.setOnClickListener {
            startActivity(intentFor<AnkoActivity>("id" to 5))
        }

        val listBtn = findViewById(R.id.list_btn) as Button
        listBtn.visibility = View.VISIBLE
        listBtn.setOnClickListener {
            startActivity<ListActivity>()
        }
        operator()
        vars(1,23,63,78,54)
        whenTest()
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

    fun operator() {
        PrintLog.e("testtag", "----------------- 操作符 -------------------")

        // 一元操作符
        PrintLog.d("testtag", "a = -5 ; +a = " + (-5).unaryPlus().toString())// +a a乘正1，-5
        PrintLog.d("testtag", "a = -5 ; -a = " + (-5).unaryMinus().toString())// -a a乘-1，5
        PrintLog.d("testtag", "a = 5 ; a++ = " + 5.inc().toString())// a++，6
        PrintLog.d("testtag", "a = 5 ; a-- = " + 5.dec().toString())// a--，4
        PrintLog.d("testtag", "a = true ; !a = " + true.not().toString())// !a a的反，false

        // 二元操作符
        PrintLog.i("testtag", "a + b ; 4 + 3 = " + 4.plus(3))// a + b 加，7
        PrintLog.i("testtag", "a - b ; 1 - 6 = " + 1.minus(6))// a - b 减，-5
        PrintLog.i("testtag", "a * b ; 4 * 2 = " + 4.times(2))// a * b 乘，8
        PrintLog.i("testtag", "a / b ; 10 / 5 = " + 10.div(5))// a / b 除，2
        PrintLog.i("testtag", "a % b ; 7 % 3 = " + 7.rem(3))// a % b 取余，1
        // a..b  闭区间运算符，从a到b范围内所有的值，包括a和b，即[a,b]
        for (i in 1..10) { // 等同于 for (i in 1.rangeTo(10))，且顺序迭代
            PrintLog.i("testtag", "i in a..b ; i in 1..10 = " + i)// a..b，1 2 3 4 5 6 7 8 9 10
        }
        // a until b  半闭区间运算符，从a到b范围内所有的值，包括a和不包括b，即[a,b)
        for (i in 1 until 10) {  // 等同于 for (i in 1.until(10))
            PrintLog.i("testtag", "i in a until b ; i in 1 until 10 = " + i)// a until b，1 2 3 4 5 6 7 8 9
        }
        // in 代表在区间内，!in表示不在。
        PrintLog.e("testtag", "6 in 1..10 = " + (6 in 1..10).toString())// a in b，true
        PrintLog.e("testtag", "36 in 1..10 = " + (36 in 1..10).toString())// a in b，false
        PrintLog.e("testtag", "6 !in 1..10 = " + (6 !in 1..10).toString())// a !in b，false
        PrintLog.e("testtag", "36 !in 1..10 = " + (36 !in 1..10).toString())// a !in b，true
        // a downTo b 逆序迭代，包括a和b
        for (i in 10 downTo 1) {
            PrintLog.w("testtag", "i in a downTo b ; i in 10 downTo 1 = " + i)// a downTo b，10 9 8 7 6 5 4 3 2 1
        }
        // i in a..b step c，在[a,b]中每隔c位打印一次
        for (i in 1..10 step 4) {
            PrintLog.d("testtag", "i in a..b step c ; i in 1..10 step 3 = " + i)// a..b,1 5 9
        }

        var a = 5
        val b = 5
        a += b // 作用等于 a = a + b，
        a -= b // 作用等于 a = a - b
        a *= b // 作用等于 a = a * b
        a /= b // 作用等于 a = a / b
        a %= b // 作用等于 a = a % b

        // 数组操作符
        PrintLog.d("testtag", "ITEMS[5] = " + ITEMS[5] + " ; ITEMS.get(5) = " + ITEMS.get(5))
        ITEMS[0] = "123"
        PrintLog.d("testtag", "ITEMS[0] = " + ITEMS[0])
        ITEMS.set(0, "456")
        PrintLog.d("testtag", "ITEMS[0] = " + ITEMS[0])

//        shl(bits) – 左移位 (Java’s <<)
//        shr(bits) – 右移位 (Java’s >>)
//        ushr(bits) – 无符号右移位 (Java’s >>>)
//        and(bits) – 与
//        or(bits) – 或
//        xor(bits) – 异或
//        inv() – 反向


    }

    /** 可变参数入参 */
    fun vars(vararg indexs:Int){
        for(item in indexs){
            PrintLog.e("testtag", item.toString())
        }
    }

    fun whenTest(){
        val x = 25
        when (x) {
            1 -> PrintLog.d("testtag", "x == 1")
            2 -> PrintLog.d("testtag", "x == 2")
            3, 4 -> PrintLog.d("testtag", "x == 3或4")
            in 1..10 -> PrintLog.d("testtag", "x is in [1,10]")
            !in 10..20 -> PrintLog.d("testtag", "x is outside [10,20]")
            else -> PrintLog.d("testtag", "其他情况")
        }
    }

}
