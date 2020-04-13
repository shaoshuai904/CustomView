package com.maple.customview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.maple.customview.R
import com.maple.customview.databinding.ViewValuePackBinding
import java.io.Serializable

/**
 * 续费价值包Adapter
 *
 * @author maple
 * @time 2019-08-06
 */
class ValuePackAdapter(val mContext: Context) : BaseQuickAdapter<ValuePackBean, RecyclerView.ViewHolder>() {
    var curSelect: ValuePackBean? = null

    fun updateCurrentSelectItem(index: Int) {
        curSelect = getItem(index)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ViewValuePackBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.view_value_pack, parent, false)
        return ValuePackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ValuePackViewHolder).bind(getItem(position))
    }

    inner class ValuePackViewHolder(val binding: ViewValuePackBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ValuePackBean) {
            bindViewClickListener(this)
            binding.apply {
                tvType.text = item.type
                tvNewValue.text = "¥ ${item.newValue}"
                tvOldValue.text = "¥ ${item.oldValue}"
                tvTime.text = "有效时间${item.time}天"

                llViewRoot.isSelected = (curSelect == item)
            }
        }
    }
}

data class ValuePackBean(
        var type: String,
        var newValue: Float,
        var oldValue: Float,
        var time: Int = 0
) : Serializable

