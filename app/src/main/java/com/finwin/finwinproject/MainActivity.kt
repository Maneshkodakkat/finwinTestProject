package com.finwin.finwinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.finwin.finwinproject.databinding.ActivityMainBinding
import com.finwin.finwinproject.utils.makeStatusBarTransparent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        makeStatusBarTransparent()
    }

}