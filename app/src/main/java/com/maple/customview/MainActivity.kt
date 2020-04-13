package com.maple.customview

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.maple.customview.adapter.BaseQuickAdapter
import com.maple.customview.adapter.ValuePackAdapter
import com.maple.customview.adapter.ValuePackBean
import com.maple.customview.databinding.ActivityMainBinding

/**
 * Custom View Demo
 *
 * @author maple
 * @time 2019/1/14
 */
class MainActivity : FragmentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mContext: Context
    private var isDownloading = true // 默认下载状态

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mContext = this
        initView()
    }


    private fun initView() {
        binding.apply {
            tbTopBar.apply {
                setTitle("new Title")
                setLeftButtonText("Back")
                setOnLeftButtonClickListener(View.OnClickListener {
                    Toast.makeText(this@MainActivity, "back", Toast.LENGTH_SHORT).show()
                })
            }
            //
            cpbStatusBar.apply {
                progress = 23
                setInterIcon(R.drawable.svg_download_ing)
                setOnClickListener {
                    if (isDownloading) {
                        setInterIcon(R.drawable.svg_download_pause)
                    } else {
                        setInterIcon(R.drawable.svg_download_ing)
                    }
                    isDownloading = !isDownloading
                }
            }
            // 自定义单选adapter
            val valuePackAdapter = ValuePackAdapter(mContext).apply {
                onItemClickListener = object : BaseQuickAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        updateCurrentSelectItem(position)
                    }
                }
            }
            rvRecords.adapter = valuePackAdapter
            valuePackAdapter.refreshData(arrayListOf(
                    ValuePackBean("单次", 0.01F, 0.01F, 2),
                    ValuePackBean("包月", 0.02F, 0.02F, 30),
                    ValuePackBean("俩月", 0.03F, 0.03F, 60)
            ))
            valuePackAdapter.updateCurrentSelectItem(0)// 默认选中
        }

    }
}
