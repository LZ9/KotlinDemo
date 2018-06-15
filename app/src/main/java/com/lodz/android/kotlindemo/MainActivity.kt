package com.lodz.android.kotlindemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.lodz.android.core.log.PrintLog
import com.lodz.android.kotlindemo.bean.Outer
import com.lodz.android.kotlindemo.bean.PlayerBean
import com.lodz.android.kotlindemo.bean.StudentBean
import com.lodz.android.kotlindemo.contract.FlyActivity
import com.lodz.android.kotlindemo.enums.ColorEnum
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
            /**
             * Jack_MUN
             * Mick_MUN
             */
        }
        for (index in list.indices) {// 迭代索引
            PrintLog.d("testtag", index.toString())
            /**
             * 0
             * 1
             */
        }

        for ((index, bean) in list.withIndex()) {//同时迭代索引和对象
            PrintLog.d("testtag", index.toString() + " ; " + bean.getTitle())
            /**
             * 0 ; Jack_MUN
             * 1 ; Mick_MUN
             */
        }

        with(list[0]) {
            PrintLog.e("testtag", name)//利用with可以直接访问到对象里的public数据
            /** Jack */
        }

        val peopleBean = list.get(0);
        PrintLog.i("testtag", peopleBean.name)
        /** Jack */

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
        /**
         * name  :  chaychan
         * age  :  22 years old
         * hobby  :  programming
         */


        val c: Char = 'c'
        val i: Int = c.toInt()


        val exampleStr = "Example"
        val array = exampleStr[2] // 这是一个字符'a'
        PrintLog.d("testtag", array.toString())
        /** a */
        for (char in exampleStr) {// 迭代String
            PrintLog.d("testtag", char.toString())
        }
        /**
         * E
         * x
         * a
         * m
         * p
         * l
         * e
         */

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

        val contractBtn = findViewById(R.id.contract_btn) as Button
        contractBtn.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                startActivity<FlyActivity>()
            }
        }

        operator()
        vars(1, 23, 63, 78, 54)
        whenTest()

        val studentBean = StudentBean()
        studentBean.name = "小明"
        studentBean.age = 8
        studentBean.grade = "三年二班"
        studentBean.studentNo = 20
        PrintLog.d("testtag", studentBean.name + " , " + studentBean.age + " , " + studentBean.grade + " , "
                + studentBean.studentNo + " , " + studentBean.major)
        /** 小明 , 8 , 人民小学-三年二班 , 21 , 数学 */

        val inner = Outer().Inner()
        PrintLog.i("testtag", "id : " + inner.getId() + " , value : " + inner.getValue())
        /** id : 12312 , value : 成员属性 */

        val tag: String? = null
        if (tag is String) {//判断对象的类型

        }

        enums()

        operatorList()

        val str: String? = String()
        // let 函数只会在 str 不是null的时候才会执行。否则它会返回null。也就是我们想达到的效果。
        str?.let { PrintLog.d("testtag", "str is not null") }




    }

    private fun jump() { //参数id为Int类型
        val intent = Intent(MainActivity@ this, NullTestActivity::class.java) //需要用::来使用Java类，注意是::（两个）
        startActivity(intent)
    }

    private fun getAddr(id: Int): String { //返回值为String类型
        return ""
    }

    private fun toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    override fun onBackPressed() {
        toast("退出")
        super.onBackPressed()
    }

    private fun operator() {
        PrintLog.e("testtag", "----------------- 操作符 -------------------")

        // 一元操作符
        PrintLog.d("testtag", "a = -5 ; +a = " + (-5).unaryPlus().toString())// +a a乘正1，-5
        /** a = -5 ; +a = -5 */
        PrintLog.d("testtag", "a = -5 ; -a = " + (-5).unaryMinus().toString())// -a a乘-1，5
        /** a = -5 ; -a = 5 */
        PrintLog.d("testtag", "a = 5 ; a++ = " + 5.inc().toString())// a++，6
        /** a = 5 ; a++ = 6 */
        PrintLog.d("testtag", "a = 5 ; a-- = " + 5.dec().toString())// a--，4
        /** a = 5 ; a-- = 4 */
        PrintLog.d("testtag", "a = true ; !a = " + true.not().toString())// !a a的反，false
        /** a = true ; !a = false */

        // 二元操作符
        PrintLog.i("testtag", "a + b ; 4 + 3 = " + 4.plus(3))// a + b 加，7
        /** a + b ; 4 + 3 = 7 */
        PrintLog.i("testtag", "a - b ; 1 - 6 = " + 1.minus(6))// a - b 减，-5
        /** a - b ; 1 - 6 = -5 */
        PrintLog.i("testtag", "a * b ; 4 * 2 = " + 4.times(2))// a * b 乘，8
        /** a * b ; 4 * 2 = 8 */
        PrintLog.i("testtag", "a / b ; 10 / 5 = " + 10.div(5))// a / b 除，2
        /** a / b ; 10 / 5 = 2 */
        PrintLog.i("testtag", "a % b ; 7 % 3 = " + 7.rem(3))// a % b 取余，1
        /** a % b ; 7 % 3 = 1 */

        // a..b  闭区间运算符，从a到b范围内所有的值，包括a和b，即[a,b]
        for (i in 1..10) { // 等同于 for (i in 1.rangeTo(10))，且顺序迭代
            PrintLog.i("testtag", "i in a..b ; i in 1..10 = " + i)// a..b，1 2 3 4 5 6 7 8 9 10
        }
        /**
         * i in a..b ; i in 1..10 = 1
         * i in a..b ; i in 1..10 = 2
         * i in a..b ; i in 1..10 = 3
         * i in a..b ; i in 1..10 = 4
         * i in a..b ; i in 1..10 = 5
         * i in a..b ; i in 1..10 = 6
         * i in a..b ; i in 1..10 = 7
         * i in a..b ; i in 1..10 = 8
         * i in a..b ; i in 1..10 = 9
         * i in a..b ; i in 1..10 = 10
         */

        // a until b  半闭区间运算符，从a到b范围内所有的值，包括a和不包括b，即[a,b)
        for (i in 1 until 10) {  // 等同于 for (i in 1.until(10))
            PrintLog.i("testtag", "i in a until b ; i in 1 until 10 = " + i)// a until b，1 2 3 4 5 6 7 8 9
        }
        /**
         * i in a until b ; i in 1 until 10 = 1
         * i in a until b ; i in 1 until 10 = 2
         * i in a until b ; i in 1 until 10 = 3
         * i in a until b ; i in 1 until 10 = 4
         * i in a until b ; i in 1 until 10 = 5
         * i in a until b ; i in 1 until 10 = 6
         * i in a until b ; i in 1 until 10 = 7
         * i in a until b ; i in 1 until 10 = 8
         * i in a until b ; i in 1 until 10 = 9
         */

        // in 代表在区间内，!in表示不在。
        PrintLog.e("testtag", "6 in 1..10 = " + (6 in 1..10).toString())// a in b，true
        /** 6 in 1..10 = true */
        PrintLog.e("testtag", "36 in 1..10 = " + (36 in 1..10).toString())// a in b，false
        /** 36 in 1..10 = false */
        PrintLog.e("testtag", "6 !in 1..10 = " + (6 !in 1..10).toString())// a !in b，false
        /** 6 !in 1..10 = false */
        PrintLog.e("testtag", "36 !in 1..10 = " + (36 !in 1..10).toString())// a !in b，true
        /** 36 !in 1..10 = true */

        // a downTo b 逆序迭代，包括a和b
        for (i in 10 downTo 1) {
            PrintLog.w("testtag", "i in a downTo b ; i in 10 downTo 1 = " + i)// a downTo b，10 9 8 7 6 5 4 3 2 1
        }
        /**
         * i in a downTo b ; i in 10 downTo 1 = 10
         * i in a downTo b ; i in 10 downTo 1 = 9
         * i in a downTo b ; i in 10 downTo 1 = 8
         * i in a downTo b ; i in 10 downTo 1 = 7
         * i in a downTo b ; i in 10 downTo 1 = 6
         * i in a downTo b ; i in 10 downTo 1 = 5
         * i in a downTo b ; i in 10 downTo 1 = 4
         * i in a downTo b ; i in 10 downTo 1 = 3
         * i in a downTo b ; i in 10 downTo 1 = 2
         * i in a downTo b ; i in 10 downTo 1 = 1
         */

        // i in a..b step c，在[a,b]中每隔c位打印一次
        for (i in 1..10 step 4) {
            PrintLog.d("testtag", "i in a..b step c ; i in 1..10 step 3 = " + i)// a..b,1 5 9
        }
        /**
         * i in a..b step c ; i in 1..10 step 3 = 1
         * i in a..b step c ; i in 1..10 step 3 = 5
         * i in a..b step c ; i in 1..10 step 3 = 9
         */

        var a = 5
        val b = 5
        a += b // 作用等于 a = a + b，
        a -= b // 作用等于 a = a - b
        a *= b // 作用等于 a = a * b
        a /= b // 作用等于 a = a / b
        a %= b // 作用等于 a = a % b

        // 数组操作符
        PrintLog.d("testtag", "ITEMS[5] = " + ITEMS[5] + " ; ITEMS.get(5) = " + ITEMS.get(5))
        /** ITEMS[5] = Sat ; ITEMS.get(5) = Sat */
        ITEMS[0] = "123"
        PrintLog.d("testtag", "ITEMS[0] = " + ITEMS[0])
        /** ITEMS[0] = 123 */
        ITEMS.set(0, "456")
        PrintLog.d("testtag", "ITEMS[0] = " + ITEMS[0])
        /** ITEMS[0] = 456 */

//        shl(bits) – 左移位 (Java’s <<)
//        shr(bits) – 右移位 (Java’s >>)
//        ushr(bits) – 无符号右移位 (Java’s >>>)
//        and(bits) – 与
//        or(bits) – 或
//        xor(bits) – 异或
//        inv() – 反向


    }

    /** 可变参数入参 */
    private fun vars(vararg indexs: Int) {
        for (item in indexs) {
            PrintLog.e("testtag", item.toString())
        }
        /**
         * 1
         * 23
         * 63
         * 78
         * 54
         */
    }

    /** when的使用方法 */
    private fun whenTest() {
        val x = 11
        when (x) {
            1 -> PrintLog.d("testtag", "x == 1")
            2 -> PrintLog.d("testtag", "x == 2")
            3, 4 -> PrintLog.d("testtag", "x == 3或4")
            in 1..10 -> PrintLog.d("testtag", "x is in [1,10]")
            !in 10..20 -> PrintLog.d("testtag", "x is outside [10,20]")
            else -> PrintLog.d("testtag", "其他情况")
        }
    }

    /** list相关操作符 */
    private fun operatorList() {
        val list = arrayListOf(2, 3, 49, 5, 7, 5, 4, 1, 1)
        val players = arrayListOf(
                PlayerBean(1, "MUN", "Jack", 25),
                PlayerBean(2, "MUN", "Jack", 21),
                PlayerBean(3, "MUN", "Jack", 31),
                PlayerBean(4, "MUN", "Jack", 21))
        val listWithNull = arrayListOf("1", null, "3", "4", "", null, "1")


        /* any */
        // 如果list里至少有一个元素符合给出的判断条件，则返回true
        PrintLog.i("testtag", "list.any { it - 1 == 0 } : " + list.any { it - 1 == 0 })
        /** list.any { it - 1 == 0 } : true */
        PrintLog.i("testtag", "list.any { it - 1 == 100 } : " + list.any { it - 1 == 100 })
        /** list.any { it - 1 == 100 } : false */

        /* all */
        // 如果list里全部的元素符合给出的判断条件，则返回true
        PrintLog.i("testtag", "list.all { it < 50 } : " + list.all { it < 50 })
        /** list.all { it < 50 } : true */
        PrintLog.i("testtag", "list.all { it < 49 } : " + list.all { it < 49 })
        /** list.all { it < 49 } : false */

        /* count */
        // 返回符合给出判断条件的元素总数
        PrintLog.i("testtag", "list.count { it % 2 == 0 } : " + list.count { it % 2 == 0 })
        /** list.count { it % 2 == 0 } : 2 */

        /* fold */
        // 在一个初始值的基础上从第一项到最后一项通过一个函数累计所有的元素。
        PrintLog.i("testtag", "list.fold(4) { total, next -> total + next } : " + list.fold(1) { total, next -> total + next })
        /** list.fold(4) { total, next -> total + next } : 78 */

        /* foldRight */
        // 与 fold 一样，但是顺序是从最后一项到第一项
        PrintLog.i("testtag", "list.foldRight(4) { total, next -> total + next } : " + list.foldRight(4) { total, next -> total + next })
        /** list.foldRight(4) { total, next -> total + next } : 81 */

        /* reduce */
        // 与 fold 一样，但是没有一个初始值。通过一个函数从第一项到最后一项进行累计。
        PrintLog.i("testtag", "list.reduce { total, next -> total + next } : " + list.reduce { total, next -> total + next })
        /** list.reduce { total, next -> total + next } : 77 */

        /* reduceRight */
        // 与 reduce 一样，但是顺序是从最后一项到第一项。
        PrintLog.i("testtag", "list.reduceRight { total, next -> total + next } : " + list.reduceRight { total, next -> total + next })
        /** list.reduceRight { total, next -> total + next } : 77 */

        /* forEach */
        // 遍历所有元素，并执行给定的操作
        list.forEach { PrintLog.i("testtag", "list.forEach : " + (it + 5)) }
        /**
         * list.forEach : 7
         * list.forEach : 8
         * list.forEach : 54
         * list.forEach : 10
         * list.forEach : 12
         * list.forEach : 10
         * list.forEach : 9
         * list.forEach : 6
         * list.forEach : 6
         */

        /* forEachIndexed */
        // 与 forEach ，但是我们同时可以得到元素的index
        list.forEachIndexed { index, value -> PrintLog.i("testtag", "list.forEachIndexed : " + index + " , " + value) }
        /**
         * list.forEachIndexed : 0 , 2
         * list.forEachIndexed : 1 , 3
         * list.forEachIndexed : 2 , 49
         * list.forEachIndexed : 3 , 5
         * list.forEachIndexed : 4 , 7
         * list.forEachIndexed : 5 , 5
         * list.forEachIndexed : 6 , 4
         * list.forEachIndexed : 7 , 1
         * list.forEachIndexed : 8 , 1
         */

        /* max */
        // 返回最大的一项，如果没有则返回null。
        PrintLog.i("testtag", "list.max() : " + list.max())
        /** list.max() : 49 */

        /* maxBy */
        // 根据给定的函数返回最大的一项，如果没有则返回null。
        PrintLog.i("testtag", "players.maxBy : " + players.maxBy { it.age }?.id)
        /** players.maxBy : 3 */

        /* min */
        // 返回最小的一项，如果没有则返回null。
        PrintLog.i("testtag", "list.min() : " + list.min())
        /** list.min() : 1 */

        /* minBy */
        // 根据给定的函数返回最小的一项，如果没有则返回null。
        PrintLog.i("testtag", "players.minBy : " + players.minBy { it.age }?.id)
        /** players.minBy : 2 */

        /* none */
        // 如果没有任何元素与给定的函数匹配，则返回true。
        PrintLog.i("testtag", "list.none { it == 10 } : " + list.none { it == 10 })
        /** list.none { it == 10 } : true */
        PrintLog.i("testtag", "list.none { it == 49 } : " + list.none { it == 49 })
        /** list.none { it == 49 } : false */

        /* sumBy */
        // 返回所有每一项通过函数转换之后的数据的总和
        PrintLog.i("testtag", "list.sumBy : " + list.sumBy { it % 2 })
        /** list.sumBy : 7 */

        /* drop */
        // 返回包含去掉前n个元素的所有元素的列表
        PrintLog.i("testtag", "list.drop(4) : " + list.drop(4).toString())
        /** list.drop(4) : [7, 5, 4, 1, 1] */

        /* dropWhile */
        // 返回根据给定函数从第一项开始去掉指定元素的列表，一旦dropWhile内返回false就停止删除，返回剩余结果集。
        PrintLog.i("testtag", "list.dropWhile : " + list.dropWhile { it < 7 }.toString())
        /** list.dropWhile : [49, 5, 7, 5, 4, 1, 1] */

        /* dropLastWhile */
        // 返回根据给定函数从最后一项开始去掉指定元素的列表，一旦dropLastWhile内返回false就停止删除，返回剩余结果集。
        PrintLog.i("testtag", "list.dropLastWhile : " + list.dropLastWhile { it < 7 }.toString())
        /** list.dropLastWhile : [2, 3, 49, 5, 7] */

        /* filter */
        // 过滤所有符合给定函数条件的元素
        PrintLog.i("testtag", "list.filter : " + list.filter { it % 2 == 0 }.toString())
        /** list.filter : [2, 4] */

        /* filterNot */
        // 过滤所有不符合给定函数条件的元素
        PrintLog.i("testtag", "list.filterNot : " + list.filterNot { it % 2 == 0 }.toString())
        /** list.filterNot : [3, 49, 5, 7, 5, 1, 1] */

        /* filterNotNull */
        // 过滤所有元素中不是null的元素
        PrintLog.i("testtag", "listWithNull.filterNotNull : " + listWithNull.filterNotNull().toString())
        /** listWithNull.filterNotNull : [1, 3, 4, , 1] */

        /* slice */
        // 过滤一个list中指定index的元素
        PrintLog.i("testtag", "list.slice(listOf(1, 3, 4)) : " + list.slice(listOf(1, 3, 4)).toString())
        /** list.slice(listOf(1, 3, 4)) : [3, 5, 7] */

        /* take */
        // 返回从第一个开始的n个元素
        PrintLog.i("testtag", "list.take(5) : " + list.take(5).toString())
        /** list.take(5) : [2, 3, 49, 5, 7] */

        /* takeLast */
        // 返回从最后一个开始的n个元素
        PrintLog.i("testtag", "list.takeLast(2) : " + list.takeLast(2).toString())
        /** list.takeLast(2) : [1, 1] */

        /* takeWhile */
        // 返回从第一个开始符合给定函数条件的元素，一旦takeWhile内返回false就停止获取，返回获取的结果集。
        PrintLog.i("testtag", "list.takeWhile : " + list.takeWhile { it < 7 }.toString())
        /** list.takeWhile : [2, 3] */

        /* takeLastWhile */
        // 返回从最后一个开始符合给定函数条件的元素，一旦takeLastWhile内返回false就停止获取，返回获取的结果集。
        PrintLog.i("testtag", "list.takeLastWhile : " + list.takeLastWhile { it < 7 }.toString())
        /** list.takeLastWhile : [5, 4, 1, 1] */

        /* flatMap */
        // 遍历所有的元素，为每一个创建一个集合，最后把所有的集合放在一个集合中。
        PrintLog.i("testtag", "list.flatMap : " + list.flatMap { listOf(it, it + 1, it * 2) }.toString())
        /** list.flatMap : [2, 3, 4, 3, 4, 6, 49, 50, 98, 5, 6, 10, 7, 8, 14, 5, 6, 10, 4, 5, 8, 1, 2, 2, 1, 2, 2] */

        /* groupBy */
        // 返回一个根据给定函数分组后的map
        PrintLog.i("testtag", "list.groupBy : " + list.groupBy {
            when (it) {
                in 1..3 -> "A"
                in 4..6 -> "B"
                7, 9 -> "C"
                else -> "D"
            }
        }.toString())
        /** list.groupBy : {A=[2, 3, 1, 1], D=[49], B=[5, 5, 4], C=[7]} */
        PrintLog.i("testtag", "list.groupBy : " + list.groupBy { if (it % 2 == 0) "even" else "odd" }.toString())
        /** list.groupBy : {even=[2, 4], odd=[3, 49, 5, 7, 5, 1, 1]} */

        /* map */
        // 返回一个每一个元素根据给定的函数转换所组成的List
        PrintLog.i("testtag", "list.map : " + list.map { it * 2 }.toString())
        /** list.map : [4, 6, 98, 10, 14, 10, 8, 2, 2] */

        /* mapIndexed */
        // 返回一个每一个元素根据给定的包含元素index的函数转换所组成的List
        PrintLog.i("testtag", "list.mapIndexed : " + list.mapIndexed { index, it ->
            if (index % 2 == 1) it * 2 else it
        }.toString())
        /** list.mapIndexed : [2, 6, 49, 10, 7, 10, 4, 2, 1] */

        /* mapNotNull */
        // 返回一个每一个非null元素根据给定的函数转换所组成的List
        PrintLog.i("testtag", "listWithNull.mapNotNull : " + listWithNull.mapNotNull { it?.plus("_no") }.toString())
        /** listWithNull.mapNotNull : [1_no, 3_no, 4_no, _no, 1_no] */

        /* contains */
        // 如果指定元素可以在集合中找到，则返回true。
        PrintLog.i("testtag", "list.contains(2) : " + list.contains(2))
        /** list.contains(2) : true */
        PrintLog.i("testtag", "list.contains(50) : " + list.contains(50))
        /** list.contains(50) : false */

        /* elementAt */
        // 返回给定index对应的元素，如果index数组越界则会抛出异常
        PrintLog.i("testtag", "list.elementAt(2) : " + list.elementAt(2))
        /** list.elementAt(2) : 49 */

        /* elementAtOrElse */
        // 返回给定index对应的元素，如果index数组越界则会根据给定函数返回默认值。
        PrintLog.i("testtag", "list.elementAtOrElse(3) : " + list.elementAtOrElse(3, { -1 }))
        /** list.elementAtOrElse(3) : 5 */
        PrintLog.i("testtag", "list.elementAtOrElse(50) : " + list.elementAtOrElse(50, { -1 }))
        /** list.elementAtOrElse(50) : -1 */

        /* elementAtOrNull */
        // 返回给定index对应的元素，如果index数组越界则会返回null。
        PrintLog.i("testtag", "list.elementAtOrNull(5) : " + list.elementAtOrNull(5))
        /** list.elementAtOrNull(5) : 5 */
        PrintLog.i("testtag", "list.elementAtOrNull(100) : " + list.elementAtOrNull(100))
        /** list.elementAtOrNull(100) : null */

        /* first */
        // 返回符合给定函数条件的第一个元素
        PrintLog.i("testtag", "list.first : " + list.first { it in 4..8 })
        /** list.first : 5 */

        /* firstOrNull */
        // 返回符合给定函数条件的第一个元素，如果没有符合则返回null
        PrintLog.i("testtag", "list.firstOrNull : " + list.firstOrNull { it > 5 })
        /** list.firstOrNull : 49 */
        PrintLog.i("testtag", "list.firstOrNull : " + list.firstOrNull { it > 100 })
        /** list.firstOrNull : null */

        /* indexOf */
        // 返回指定元素的第一个index，如果不存在，则返回 -1
        PrintLog.i("testtag", "list.indexOf(7) : " + list.indexOf(7))
        /** list.indexOf(7) : 4 */
        PrintLog.i("testtag", "list.indexOf(100) : " + list.indexOf(100))
        /** list.indexOf(100) : -1 */

        /* indexOfFirst */
        // 返回第一个符合给定函数条件的元素的index，如果没有符合则返回 -1
        PrintLog.i("testtag", "list.indexOfFirst : " + list.indexOfFirst { it in 3..8 })
        /** list.indexOfFirst : 1 */
        PrintLog.i("testtag", "list.indexOfFirst : " + list.indexOfFirst { it in 300..800 })
        /** list.indexOfFirst : -1 */

        /* indexOfLast */
        // 返回最后一个符合给定函数条件的元素的index，如果没有符合则返回 -1
        PrintLog.i("testtag", "list.indexOfLast : " + list.indexOfLast { it in 3..8 })
        /** list.indexOfLast : 6 */
        PrintLog.i("testtag", "list.indexOfLast : " + list.indexOfLast { it in 300..800 })
        /** list.indexOfLast : -1 */

        /* last */
        // 返回符合给定函数条件的最后一个元素，没有找到时会抛出异常
        PrintLog.i("testtag", "list.last : " + list.last { it in 4..8 })
        /** list.last : 4 */

        /* lastIndexOf */
        // 返回指定元素的最后一个index，如果不存在，则返回 -1
        PrintLog.i("testtag", "listWithNull.lastIndexOf : " + listWithNull.lastIndexOf("1"))
        /** listWithNull.lastIndexOf : 6 */
        PrintLog.i("testtag", "listWithNull.lastIndexOf : " + listWithNull.lastIndexOf("99"))
        /** listWithNull.lastIndexOf : -1 */

        /* lastOrNull */
        // 返回符合给定函数条件的最后一个元素，如果没有符合则返回null
        PrintLog.i("testtag", "list.lastOrNull : " + list.lastOrNull { it in 2..8 })
        /** list.lastOrNull : 4 */
        PrintLog.i("testtag", "list.lastOrNull : " + list.lastOrNull { it in 200..500 })
        /** list.lastOrNull : null */

        /* single */
        // 返回符合给定函数的单个元素，如果没有符合或者超过一个，则抛出异常。
        PrintLog.i("testtag", "list.single : " + list.single { it == 49 })
        /** list.single : 49 */

        /* singleOrNull */
        // 返回符合给定函数的单个元素，如果没有符合或者超过一个，则返回null。
        PrintLog.i("testtag", "list.singleOrNull : " + list.singleOrNull { it == 49 })
        /** list.singleOrNull : 49 */
        PrintLog.i("testtag", "list.singleOrNull : " + list.singleOrNull { it == 1 })
        /** list.singleOrNull : null */

        /* partition */
        // 把一个给定的集合分割成两个，第一个集合是由原集合每一项元素匹配给定函数条件返回 true 的元素组成，第二个集合是由原集合每一项元素匹配给定函数条件返回 false 的元素组成。
        val pair = list.partition { it % 2 == 0 }
        PrintLog.i("testtag", "list.partition first : " + pair.first.toString())
        /** list.partition first : [2, 4] */
        PrintLog.i("testtag", "list.partition second : " + pair.second.toString())
        /** list.partition second : [3, 49, 5, 7, 5, 1, 1] */

        /* plus */
        // 返回一个包含原集合和给定集合中所有元素的集合，因为函数的名字原因，我们可以使用 + 操作符。
        PrintLog.i("testtag", "list.plus 1 : " + (list + listOf(7, 8)).toString())// 可以用 + 操作符替代
        /** list.plus 1 : [2, 3, 49, 5, 7, 5, 4, 1, 1, 7, 8] */
        PrintLog.i("testtag", "list.plus 2 : " + list.plus(1).toString())//增加一个元素
        /** list.plus 2 : [2, 3, 49, 5, 7, 5, 4, 1, 1, 1] */
        PrintLog.i("testtag", "list.plus 3 : " + list.plus(listOf(54, 11)).toString())
        /** list.plus 3 : [2, 3, 49, 5, 7, 5, 4, 1, 1, 54, 11] */

        /* reversed */
        // 返回一个与指定list相反顺序的list
        list.reverse()// 逆序数据，无返回值
        PrintLog.i("testtag", "list.reverse : " + list.toString())
        /** list.reverse : [1, 1, 4, 5, 7, 5, 49, 3, 2] */
        PrintLog.i("testtag", "list.reversed : " + list.reversed().toString())//返回逆序后的list
        /** list.reversed : [2, 3, 49, 5, 7, 5, 4, 1, 1] */

        /* sorted */
        // 返回一个自然排序后的list
        list.sort()// 从小到大排序，无返回值
        PrintLog.i("testtag", "list.sort : " + list.toString())
        /** list.sort : [1, 1, 2, 3, 4, 5, 5, 7, 49] */
        PrintLog.i("testtag", "list.sorted : " + list.plus(5).sorted().toString())//返回排序后的list
        /** list.sorted : [1, 1, 2, 3, 4, 5, 5, 5, 7, 49] */

        /* sortedBy */
        // 返回一个根据指定函数排序后的list
        PrintLog.i("testtag", "list.sortedBy : " + list.sortedBy { it % 5 }.toString())
        /** list.sortedBy : [5, 5, 1, 1, 2, 7, 3, 4, 49] */

        /* sortedDescending */
        // 返回一个降序排序后的List
        PrintLog.i("testtag", "list.sortedDescending : " + list.sortedDescending().toString())
        /** list.sortedDescending : [49, 7, 5, 5, 4, 3, 2, 1, 1] */

        /* sortedByDescending */
        // 返回一个根据指定函数降序排序后的list
        PrintLog.i("testtag", "list.sortedByDescending : " + list.sortedByDescending { it % 5 }.toString())
        /** list.sortedByDescending : [4, 49, 3, 2, 7, 1, 1, 5, 5] */



    }

    /** 枚举测试 */
    private fun enums() {
        PrintLog.e("testtag", "ColorEnum.GREEN : " + ColorEnum.GREEN)
        PrintLog.e("testtag", "ColorEnum.GREEN : " + ColorEnum.GREEN.color)
        PrintLog.e("testtag", "ColorEnum.GREEN.name : " + ColorEnum.GREEN.name)
        PrintLog.e("testtag", "ColorEnum.GREEN.ordinal : " + ColorEnum.GREEN.ordinal)
        PrintLog.e("testtag", "valueOf : " + ColorEnum.valueOf("GREEN"))
        PrintLog.e("testtag", "values : " + ColorEnum.values().joinToString { it.name})
        /**
         * ColorEnum.GREEN : GREEN
         * ColorEnum.GREEN : 16711680
         * ColorEnum.GREEN.name : GREEN
         * ColorEnum.GREEN.ordinal : 1
         * valueOf : GREEN
         * values : RED, GREEN, BLUE
         */

    }
}
