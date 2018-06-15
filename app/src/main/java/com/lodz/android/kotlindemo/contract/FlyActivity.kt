package com.lodz.android.kotlindemo.contract

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import com.lodz.android.kotlindemo.R

/**
 * 接口测试类
 * Created by zhouL on 2018/6/15.
 */
class FlyActivity : AppCompatActivity() {

    @BindView(R.id.fly_btn)
    lateinit var mFlyBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fly)
        ButterKnife.bind(this)

        mFlyBtn.setOnClickListener {
            fly()
        }
    }

    fun fly() {
        val birdWithWings = Bird(AnimalWithWings())
        birdWithWings.fly()

        val bat = Bat()
        bat.fly()
    }

}