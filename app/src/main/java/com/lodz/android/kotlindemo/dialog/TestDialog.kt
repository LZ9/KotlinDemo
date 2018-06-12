package com.lodz.android.kotlindemo.dialog

import android.content.Context
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.lodz.android.component.widget.dialog.BaseDialog
import com.lodz.android.kotlindemo.NullTestActivity
import com.lodz.android.kotlindemo.R
import org.jetbrains.anko.startActivity

/**
 * 测试弹框
 * Created by zhouL on 2018/6/11.
 */
class TestDialog(context: Context) : BaseDialog(context) {

    @BindView(R.id.test_tv)
    lateinit var mTestTv: TextView

    override fun getLayoutId(): Int = R.layout.dialog_test

    override fun findViews() {
        ButterKnife.bind(this)
    }

    override fun setListeners() {
        super.setListeners()
        mTestTv.setOnClickListener {
            context.startActivity<NullTestActivity>()
        }
    }
}