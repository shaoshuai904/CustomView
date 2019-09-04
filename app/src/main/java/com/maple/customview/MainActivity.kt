package com.maple.customview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val valuePackAdapter by lazy {
        ValuePackAdapter(this).apply {
            onItemClickListener = object : BaseQuickAdapter.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {

                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

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
            rvRecords.apply {
                adapter = valuePackAdapter
            }
        }

        valuePackAdapter.refreshData(getTestData())
    }


    fun getTestData(): List<ValuePackBean> {
        return arrayListOf(
                ValuePackBean("单次", "0.01", "0.01", 2),
                ValuePackBean("包月", "0.02", "0.02", 30),
                ValuePackBean("俩月", "0.03", "0.03", 60)
        )
    }

}
