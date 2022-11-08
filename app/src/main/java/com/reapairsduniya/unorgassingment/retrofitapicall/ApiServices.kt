package com.example.mymvvm.retrofitApiCall

import com.example.mymvvm.constants.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private fun getokHttpClient() : OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)  /** this line comment when you publish app */
        .build()
}

private fun getRetrofitBuilder(URL : String) : Retrofit.Builder{
    return  Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getokHttpClient())

}

fun <T> getRetrofitService(clazz : Class<T>) : T {
    return  getRetrofitBuilder(BASE_URL).build().create(clazz)
}

