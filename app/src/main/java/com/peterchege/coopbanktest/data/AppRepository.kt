package com.peterchege.coopbanktest.data

import com.peterchege.coopbanktest.core.api.CoopBankService
import com.peterchege.coopbanktest.core.api.payloads.LoginUser
import com.peterchege.coopbanktest.core.api.responses.LoginResponse
import com.peterchege.coopbanktest.core.api.safeApiCall
import com.peterchege.coopbanktest.core.util.NetworkResult
import javax.inject.Inject

interface AppRepository {

    suspend fun loginUser(loginUser: LoginUser):NetworkResult<LoginResponse>
}

class AppRepositoryImpl @Inject constructor(
    private val apiService:CoopBankService
):AppRepository{

    override suspend fun loginUser(loginUser: LoginUser):NetworkResult<LoginResponse>{
        return safeApiCall { apiService.loginUser(loginUser) }
    }


}