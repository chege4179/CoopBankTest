package com.peterchege.coopbanktest.core.api

import com.peterchege.coopbanktest.core.util.NetworkResult
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

suspend fun <T : Any> safeApiCall(
    execute: suspend () -> Response<T>
): NetworkResult<T> {
    val TAG = "Network Error"
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else {
            NetworkResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        Timber.tag(TAG).i("The HTTP error causing this is -----> $e")
        NetworkResult.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        Timber.tag(TAG).i("The HTTP error causing this is -----> $e")
        NetworkResult.Exception(e)
    }
}