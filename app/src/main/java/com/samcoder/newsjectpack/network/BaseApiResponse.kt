package com.samcoder.newsjectpack.network

import org.json.JSONObject
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>):NetworkResult<T>{
        try {
          val response = apiCall()
            if (response.isSuccessful){
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }

                val jObjError = JSONObject(response.errorBody()!!.string())
                val errorMessage = jObjError.getJSONObject("Error").getString("Message")

                return error(errorMessage)


        }catch (e:Exception){
            e.printStackTrace()
            return error(e.message?: e.toString())
        }
        }



    private fun <T>error(error:String) : NetworkResult<T> = NetworkResult.Error(error)
}