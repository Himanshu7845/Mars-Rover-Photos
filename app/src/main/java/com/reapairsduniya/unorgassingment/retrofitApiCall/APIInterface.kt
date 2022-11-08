package com.example.mymvvm.retrofitApiCall

import android.service.autofill.UserData
import com.example.mymvvm.constants.API_KEY
import com.reapairsduniya.unorgassingment.model.MarsRoverData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface
{


    /*
     with the help of this api rovers Curiosity,Spirit and Opportunity ye teeno
     ka particular sol me click kiya wala data dekhega

     */
    @GET("photos")
    suspend fun getRoverBySol(@Query("sol") sol: String,@Query ("api_key") api_key:String):Response<MarsRoverData>
}