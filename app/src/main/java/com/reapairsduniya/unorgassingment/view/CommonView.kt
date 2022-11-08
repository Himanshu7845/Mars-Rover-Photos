package com.reapairsduniya.unorgassingment.view

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
import com.reapairsduniya.unorgassingment.databinding.ActivityCommonViewBinding
import com.reapairsduniya.unorgassingment.util.showToast
import kotlinx.coroutines.launch

class CommonView : AppCompatActivity() {
    lateinit var binding:ActivityCommonViewBinding
    var totalSol:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_common_view)
        binding.apply {
            fetch.setOnClickListener {
                validation(binding)
            }
        }
        lifecycleScope.launch {
            val myViewModel = MyViewModel()
            myViewModel.getRoverInfo("curiosity", API_KEY)
            myViewModel.getRoverInfoLiveData.observe(this@CommonView, Observer {
                when (it) {
                    is ResultWrappers.Error -> showToast("Error")
                    is ResultWrappers.Loading -> showToast("Loading")
                    is ResultWrappers.Success -> {
                        Log.d("AllData", "onCreate: ${it.data!!.rover.max_sol}")
                        totalSol=it.data!!.rover.max_sol.toInt()
                    }
                }
            })
        }
    }

    private fun validation(binding: ActivityCommonViewBinding) {
      binding.apply {

          if(edt1.text.toString().trim().isNullOrEmpty()){
              showToast("Please Provide SOL")
          }
          else if(edt1.text.toString().toInt()>totalSol && edt1.text.toString().toInt() >0){
              showToast("please enter sol value smaller than $totalSol")
           //  fetch.isEnabled=false
          }
          else{
              //hitapi()
          }
      }
    }
}