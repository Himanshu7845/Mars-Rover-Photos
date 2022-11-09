package com.repairsduniya.android.Repository

import com.example.mymvvm.retrofitApiCall.APIInterface
import com.reapairsduniya.unorgassingment.model.roverdatamodel.MarsRoverData
import com.reapairsduniya.unorgassingment.model.roverinfomodel.RoverInfo
import retrofit2.Response

class MyReposiratory(val apiInterface: APIInterface)
{
    suspend fun getRoverBySol(sol: String, api_key: String, roverName: String):Response<MarsRoverData>{
       return apiInterface.getRoverBySol(sol = sol, api_key = api_key, body = roverName)
    }

    suspend fun getRoverInfo(body:String,api_key:String):Response<RoverInfo> {
       return apiInterface.getRoverInfo(body,api_key)
    }
}