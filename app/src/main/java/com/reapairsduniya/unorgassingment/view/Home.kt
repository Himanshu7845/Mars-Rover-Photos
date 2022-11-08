package com.reapairsduniya.unorgassingment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reapairsduniya.unorgassingment.R
import com.reapairsduniya.unorgassingment.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_home)
        binding.apply {
            c1.setOnClickListener {
               startActivity(Intent(this@Home,CommonView::class.java))
            }
            c2.setOnClickListener {

            }
            c3.setOnClickListener {

            }
        }

    }
}