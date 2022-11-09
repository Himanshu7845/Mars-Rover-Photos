package com.reapairsduniya.unorgassingment.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.reapairsduniya.unorgassingment.R
import com.reapairsduniya.unorgassingment.databinding.ActivityRoverInfoBinding
import com.reapairsduniya.unorgassingment.model.alldatamodel.AllDataModel
import com.reapairsduniya.unorgassingment.model.alldatamodel.SetSomeData


class RoverInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityRoverInfoBinding
    lateinit var collectData: AllDataModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rover_info)


        val data = intent.getStringExtra("data").toString()

        if (!data.isNullOrEmpty()) {
            collectData = Gson().fromJson(data, AllDataModel::class.java)
            Log.d("collectAllData", "onCreate: ${collectData.toString()}")
            binding.apply {
                var datas = SetSomeData(
                    sol = "SOL:",
                    name = "Rover Name:",
                    cameraUsed = "Camera Used:",
                    status = "Status:",
                    landingDate = "LandingDate:",
                    launchingDate = "LaunchingDate:",
                    photoClickedDate = "Photo Clicked On:"
                )
                setSomeData = datas
                setData = collectData

            }
            Glide.with(this).load(collectData.img_src).into(binding.imageView)
        }

        binding.sideImage.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Hi there check this out some cool pics clicked by nasa rovers\n"+
                        "${collectData.img_src} \n ${binding.heading.text} \n"+
                        "${binding.launchDate.text} \n"+
                        "${binding.cameraUsed.text} \n"+
                        "${binding.sol.text} \n"+
                        "${binding.photoClicked.text} \n"+
                        "${binding.landingDate.text} \n"+
                        "${binding.status.text}"

            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Send"))
        }

    }
}