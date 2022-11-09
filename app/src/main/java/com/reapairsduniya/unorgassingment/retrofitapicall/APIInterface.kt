package com.example.mymvvm.retrofitApiCall

import com.reapairsduniya.unorgassingment.model.roverdatamodel.MarsRoverData
import com.reapairsduniya.unorgassingment.model.roverinfomodel.Rover
import com.reapairsduniya.unorgassingment.model.roverinfomodel.RoverInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {


    /*
     with the help of this api rovers Curiosity,Spirit and Opportunity ye teeno
     ka particular sol me click kiya wala data dekhega

     */
    @GET("rovers/{roverName}/photos")
    suspend fun getRoverBySol(
        @Path("roverName") body: String,
        @Query("sol") sol: String,
        @Query("api_key") api_key: String
    ): Response<MarsRoverData>


    //curiosity?api_key=2HsXU3YMMSDyoGvrDv45noms7E8okXxZcw0rDPxq
    @GET("rovers/{roverName}")
    suspend fun getRoverInfo(
        @Path("roverName") body: String,
        @Query("api_key") api_key: String
    ): Response<RoverInfo>


}