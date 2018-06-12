package com.lodz.android.kotlindemo.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.lodz.android.component.widget.adapter.recycler.BaseRecyclerViewAdapter
import com.lodz.android.kotlindemo.R
import com.lodz.android.kotlindemo.bean.PlayerBean

/**
 * 列表适配器
 * Created by zhouL on 2018/6/12.
 */
class ListAdapter(context: Context) : BaseRecyclerViewAdapter<PlayerBean>(context) {

    /** id点击监听器 */
    private var mClickIdListener: OnClickIdListener? = null
    /** 俱乐部点击监听器 */
    private var mClickClubListener: ((PlayerBean) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DataViewHolder(getLayoutView(parent, R.layout.item_list))
    }

    override fun onBind(holder: RecyclerView.ViewHolder?, position: Int) {
        val bean = getItem(position)
        if (bean == null) {
            return
        }
        showItem(holder as DataViewHolder, bean)
    }

    private fun showItem(holder: DataViewHolder, bean: PlayerBean) {
        holder.idTv.text = bean.id.toString()
        holder.nameTv.text = bean.name
        holder.ageTv.text = bean.age.toString()
        holder.clubTv.text = bean.club
        holder.idTv.setOnClickListener {
            mClickIdListener?.onClick(bean)
        }

        holder.clubTv.setOnClickListener {
            mClickClubListener?.invoke(bean)
        }
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.id)
        lateinit var idTv: TextView
        @BindView(R.id.name)
        lateinit var nameTv: TextView
        @BindView(R.id.age)
        lateinit var ageTv: TextView
        @BindView(R.id.club)
        lateinit var clubTv: TextView

        init {
            ButterKnife.bind(this, itemView);
        }
    }

    fun setOnClickClubListener(listener: (PlayerBean) -> Unit) {
        mClickClubListener = listener
    }

    fun setOnClickIdListener(listener: OnClickIdListener) {
        mClickIdListener = listener
    }


    interface OnClickIdListener {
        fun onClick(bean: PlayerBean)
    }
}