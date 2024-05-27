package com.samcoder.newsjectpack.network

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData

/** Created by SamCoder */
sealed class NetworkResult<T>(
    val data:T?= null,
    val error:String? = null
) {
    class Empty<T> : NetworkResult<T>()
    class Success<T>(data: T):NetworkResult<T>(data)
    class Error<T>(error: String?,data: T? = null):NetworkResult<T>(data,error)
    class loading<T>:NetworkResult<T>()
}

fun <T> NetworkResult<T>.resultHandler(
    loading:() -> Unit={},
    error:() -> Unit = {},
    success:() -> Unit = {}
){
  when(this){
      is NetworkResult.Empty -> {

      }
      is NetworkResult.Error -> {
          error()
      }
      is NetworkResult.Success -> {
          success()
      }
      is NetworkResult.loading -> {

          loading()
      }
  }
}

fun <T> webResponse() = mutableStateOf<NetworkResult<T>>(NetworkResult.Empty())