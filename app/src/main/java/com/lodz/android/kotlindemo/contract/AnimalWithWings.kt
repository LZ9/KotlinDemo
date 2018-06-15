package com.lodz.android.kotlindemo.contract

/**
 *
 * Created by zhouL on 2018/6/15.
 */
class AnimalWithWings : CanFly {
    val wings: Wings = Wings()
    override fun fly() = wings.move()
}