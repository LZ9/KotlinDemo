package com.lodz.android.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.lodz.android.core.log.PrintLog
import com.lodz.android.kotlindemo.dialog.TestDialog
import org.jetbrains.anko.*

/**
 * Anko测试类
 * Created by zhouL on 2018/6/11.
 */
class AnkoActivity : AppCompatActivity() {


    // 支持butterknife
    @BindView(R.id.text_tv)
    lateinit var mTextTv: TextView
    @BindView(R.id.jump_btn)
    lateinit var mJumpBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        val ankoBtn = find<Button>(R.id.anko_btn)
        ankoBtn.visibility = View.VISIBLE;
        ankoBtn.text = "test dialog"
        ankoBtn.setOnClickListener {
            val dialog = TestDialog(this);
            dialog.show()
        }

        mTextTv.text = "测试"
        mTextTv.textSize = 16f


        mJumpBtn.text = "jump"
        mJumpBtn.setOnClickListener {
//            makeCall("123456")// 打电话
//            sendSMS("123456", "test")// 发短信
            browse("http://www.baidu.com/", true)// 打开浏览器
//            share("test", "the test subject")// 分享
//            email("abc@foxmail.com", "the test subject", "test")

            toast("Hi there!")
        }

//        startActivity<NullTestActivity>()
//        startActivity(intentFor<AnkoActivity>("id" to 5))

        doAsync {
            PrintLog.d("testtag", Thread.currentThread().name)// 异步线程
            uiThread {
                PrintLog.i("testtag", Thread.currentThread().name)//主线程
            }


        }

    }
}