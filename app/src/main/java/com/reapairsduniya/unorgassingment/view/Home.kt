package com.reapairsduniya.unorgassingment.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.reapairsduniya.unorgassingment.R
import com.reapairsduniya.unorgassingment.databinding.ActivityHomeBinding


class Home : AppCompatActivity() {
    var doubleBackToExitPressedOnce = false
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.apply {
            c1.setOnClickListener {
                roverName = "curiosity"
                startActivity(Intent(this@Home, CommonView::class.java))
            }
            c2.setOnClickListener {
                roverName = "spirit"
                startActivity(Intent(this@Home, CommonView::class.java))
            }
            c3.setOnClickListener {
                roverName = "opportunity"
                startActivity(Intent(this@Home, CommonView::class.java))
            }
        }
    }
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 1000)
    }
    companion object{
        lateinit var roverName:String
    }
}