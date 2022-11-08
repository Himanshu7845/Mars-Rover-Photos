package com.reapairsduniya.admin.MyViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymvvm.retrofitApiCall.APIInterface
import com.example.mymvvm.retrofitApiCall.getRetrofitService
import com.reapairsduniya.admin.ResultWrapper.ResultWrappers
import com.reapairsduniya.unorgassingment.model.MarsRoverData
import com.repairsduniya.android.Repository.MyReposiratory
import kotlinx.coroutines.launch

class MyViewModel:ViewModel()
{
    val mutablePinCodeLiveData = MutableLiveData<ResultWrappers<MarsRoverData>>()
    val livePinCodeData: LiveData<ResultWrappers<MarsRoverData>>
        get() = mutablePinCodeLiveData
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
}