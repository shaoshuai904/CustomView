package com.maple.customview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.maple.customview.databinding.ActivityMainBinding

/**
 * Custom View Demo
 *
 * @author maple
 * @time 2019/1/14
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initView()
    }

    private fun initView() {
        binding.tbTopBar.apply {
            setTitle("new Title")
            setLeftButtonText("Back")
            setOnLeftButtonClickListener(View.OnClickListener {
                Toast.makeText(this@MainActivity, "back", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
