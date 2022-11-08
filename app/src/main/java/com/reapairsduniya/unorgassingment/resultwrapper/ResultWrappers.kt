package com.reapairsduniya.admin.ResultWrapper

sealed class ResultWrappers<T>(val data: T? = null,val message: String? = null)
{
    class Error<T>(message: String?, data: T? = null) : ResultWrappers<T>(data, message)
    class Loading<T> : ResultWrappers<T>()
    class Success<T>(data: T) : ResultWrappers<T>(data)
}
