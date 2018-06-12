package com.lodz.android.kotlindemo.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.lodz.android.component.widget.adapter.decoration.RoundItemDecoration
import com.lodz.android.kotlindemo.R
import com.lodz.android.kotlindemo.bean.PlayerBean
import org.jetbrains.anko.toast
import java.util.*

/**
 * 列表页
 * Created by zhouL on 2018/6/12.
 */
class ListActivity : AppCompatActivity() {

    @BindView(R.id.recycler_view)
    lateinit var mRecyclerView: RecyclerView

    lateinit var mAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        ButterKnife.bind(this)

        initRecyclerView()
        setListeners()
        initData()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mAdapter = ListAdapter(this)
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.addItemDecoration(RoundItemDecoration.createBottomDivider(this, 1, android.R.color.darker_gray, android.R.color.white, 5))
        mRecyclerView.adapter = mAdapter
    }

    private fun setListeners() {

        mAdapter.setOnClickIdListener(object : ListAdapter.OnClickIdListener {
            override fun onClick(bean: PlayerBean) {
                toast(bean.id.toString())
            }
        })

        mAdapter.setOnClickClubListener { bean: PlayerBean ->
            toast(bean.id.toString() + "  " + bean.club)
        }

    }

    private fun initData() {
        val list = ArrayList<PlayerBean>()
        for (i in 1..20) {
            list.add(PlayerBean(i, "皇家马德里", "C罗", Random().nextInt(20) + 18))
        }
        mAdapter.setData(list)
        mAdapter.notifyDataSetChanged()
    }

}
