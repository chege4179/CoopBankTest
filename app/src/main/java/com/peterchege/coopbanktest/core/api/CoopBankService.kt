package com.peterchege.coopbanktest.core.api

import com.peterchege.coopbanktest.core.api.payloads.LoginUser
import com.peterchege.coopbanktest.core.api.responses.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CoopBankService {

    @POST("/auth/login")
    suspend fun loginUser(@Body loginUser: LoginUser): Response<LoginResponse>

}