package com.example.mymvvm.retrofitApiCall

import com.example.mymvvm.constants.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//2nd phase
private fun getokHttpClient() : OkHttpClient {
    ////headerInterceptor
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder()
       // .addInterceptor(ApiKeyInterceptor(KEY))
        .addInterceptor(loggingInterceptor)  /** this line comment when you publish app */
        .build()
}

//1st phase
/**
private fun getRetrofitBuilder(URL : String, KEY : String) : Retrofit.Builder{
    return  Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getokHttpClient(KEY))
}**/

private fun getRetrofitBuilder(URL : String) : Retrofit.Builder{
    return  Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getokHttpClient())

}

fun <T> getRetrofitService(clazz : Class<T>) : T {
    return  getRetrofitBuilder(BASE_URL).build().create(clazz)
}

//
///** For NEW URL */
//fun <T> getRetrofitService(clazz : Class<T>) : T {
//    return  getRetrofitBuilder(BASE_URL, KEY).build().create(clazz)
//}
//
///** For NGRok URL */
//fun <T> getLoginRetrofitService(clazz : Class<T>) : T {
//    return  getRetrofitBuilder(DOMAIN_URL, DOMAIN_KEY).build().create(clazz)
//}

//headerInterceptor
class ApiKeyInterceptor(val key : String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder  = chain.request().newBuilder()
        requestBuilder.addHeader("Authkey",
            "$key")
        return  chain.proceed(requestBuilder.build())
    }
}