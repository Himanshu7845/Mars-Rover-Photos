package com.reapairsduniya.unorgassingment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.mymvvm.constants.API_KEY
import com.reapairsduniya.admin.MyViewModel.MyViewModel
import com.reapairsduniya.admin.ResultWrapper.ResultWrappers
import com.reapairsduniya.unorgassingment.R
import com.reapairsduniya.unorgassingment.databinding.ActivityHomeBinding
import com.reapairsduniya.unorgassingment.util.showToast
import kotlinx.coroutines.launch

class Home : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.apply {
            c1.setOnClickListener {
                startActivity(Intent(this@Home, CommonView::class.java))
            }
            c2.setOnClickListener {

            }
            c3.setOnClickListener {

            }
        }

        /* lifecycleScope.launch {
             val myViewModel=MyViewModel()
             myViewModel.getRoverData("100", API_KEY)
             myViewModel.livePinCodeData.observe(this@Home, Observer {
                 when(it){
                     is ResultWrappers.Error -> showToast("Error")
                     is ResultWrappers.Loading -> showToast("Loading")
                     is ResultWrappers.Success ->{
                         Log.d("AllData", "onCreate: ${it.data}")
                     }
                 }
             })
         }*/
        lifecycleScope.launch {
            val myViewModel = MyViewModel()
            myViewModel.getRoverInfo("curiosity", API_KEY)
            myViewModel.getRoverInfoLiveData.observe(this@Home, Observer {
                when (it) {
                    is ResultWrappers.Error -> showToast("Error")
                    is ResultWrappers.Loading -> showToast("Loading")
                    is ResultWrappers.Success -> {
                        Log.d("AllData", "onCreate: ${it.toString()}")
                    }
                }
            })
        }

    }
}