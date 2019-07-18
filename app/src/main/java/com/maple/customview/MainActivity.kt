package com.maple.customview

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.maple.customview.databinding.ActivityMainBinding

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
