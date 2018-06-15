package com.lodz.android.kotlindemo.contract

/**
 * 鸟
 * Created by zhouL on 2018/6/15.
 */
class Bird(fly: CanFly) : CanFly by fly