package com.reapairsduniya.unorgassingment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reapairsduniya.unorgassingment.R
import com.reapairsduniya.unorgassingment.databinding.ActivityCommonViewBinding
import com.reapairsduniya.unorgassingment.util.showToast

class CommonView : AppCompatActivity() {
    lateinit var binding:ActivityCommonViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_common_view)
        binding.apply {
            edt1.setOnClickListener {
                validation(binding)
            }
        }
    }

    private fun validation(binding: ActivityCommonViewBinding) {
      binding.apply {
          if(edt1.text.toString().trim().isNullOrEmpty()){
              showToast("Please Provide SOL")
          }
          else{
             fetch.isEnabled=false
          }
      }
    }
}