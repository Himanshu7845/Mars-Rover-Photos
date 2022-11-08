package com.repairsduniya.android.Repository

import com.example.mymvvm.retrofitApiCall.APIInterface
import com.reapairsduniya.unorgassingment.model.MarsRoverData
import retrofit2.Response

class MyReposiratory(val apiInterface: APIInterface)
{
    suspend fun getRoverBySol(sol:String,api_key:String):Response<MarsRoverData>{
       return apiInterface.getRoverBySol(sol,api_key)
    }
}