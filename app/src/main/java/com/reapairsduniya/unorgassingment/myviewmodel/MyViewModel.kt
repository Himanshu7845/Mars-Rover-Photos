package com.reapairsduniya.admin.MyViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymvvm.retrofitApiCall.APIInterface
import com.example.mymvvm.retrofitApiCall.getRetrofitService
import com.reapairsduniya.admin.ResultWrapper.ResultWrappers
import com.reapairsduniya.unorgassingment.model.roverdatamodel.MarsRoverData
import com.reapairsduniya.unorgassingment.model.roverdatamodel.Rover
import com.reapairsduniya.unorgassingment.model.roverinfomodel.RoverInfo
import com.repairsduniya.android.Repository.MyReposiratory
import kotlinx.coroutines.launch

class MyViewModel:ViewModel()
{
    val mutablePinCodeLiveData = MutableLiveData<ResultWrappers<MarsRoverData>>()
    val livePinCodeData: LiveData<ResultWrappers<MarsRoverData>>
        get() = mutablePinCodeLiveData


    val mutableGetRoverInfoLiveData = MutableLiveData<ResultWrappers<RoverInfo>>()
    val getRoverInfoLiveData: LiveData<ResultWrappers<RoverInfo>>
        get() = mutableGetRoverInfoLiveData


    suspend fun getRoverData(sol:String,api_key:String){
        viewModelScope.launch {
            mutablePinCodeLiveData.value=ResultWrappers.Loading()
            val service = getRetrofitService(APIInterface::class.java)
            val repo=MyReposiratory(service)
            val result= repo.getRoverBySol(sol, api_key = api_key)
            if(result.isSuccessful && result.body()!=null)
            {
                mutablePinCodeLiveData.value=ResultWrappers.Success(result.body()!!)
                Log.d("pinCodeResponse", "getPinCode: ${result.body()}")
            }
            else if(result.errorBody()!=null)
            {
                mutablePinCodeLiveData.value=ResultWrappers.Error("Something Went Wrong")
            }
            else{
                mutablePinCodeLiveData.value=ResultWrappers.Error("Something Went Wrong")
            }

        }
    }


    suspend fun getRoverInfo(body:String,api_key:String){
        viewModelScope.launch {
            mutableGetRoverInfoLiveData.value=ResultWrappers.Loading()
            val service = getRetrofitService(APIInterface::class.java)
            val repo=MyReposiratory(service)
            val result= repo.getRoverInfo(body,api_key)
            if(result.isSuccessful && result.body()!=null)
            {
                mutableGetRoverInfoLiveData.value=ResultWrappers.Success(result.body()!!)
                Log.d("getDataRover", "${result.body()}")
            }
            else if(result.errorBody()!=null)
            {
                mutableGetRoverInfoLiveData.value=ResultWrappers.Error("Something Went Wrong")
            }
            else{
                mutableGetRoverInfoLiveData.value=ResultWrappers.Error("Something Went Wrong")
            }

        }
    }
}