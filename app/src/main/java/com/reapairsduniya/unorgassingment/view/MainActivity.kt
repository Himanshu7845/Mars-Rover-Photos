package com.reapairsduniya.unorgassingment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.reapairsduniya.unorgassingment.R
import com.reapairsduniya.unorgassingment.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        zoom()
        lifecycleScope.launch {
            delay(3000)
            binding.pBar.visibility = View.GONE
            startActivity(Intent(this@MainActivity, Home::class.java))
        }
        binding.pBar.visibility = View.VISIBLE
    }

    private fun zoom() {
        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_in)
        binding.materialCardView.startAnimation(animation)
    }
}