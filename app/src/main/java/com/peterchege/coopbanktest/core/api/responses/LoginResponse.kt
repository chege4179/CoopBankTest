package com.peterchege.coopbanktest.core.api.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class LoginResponse(
    val id:Int,
    val username:String,
    val email:String,
    val firstName:String,
    val lastName:String,
    val gender:String,
    val image:String,
    val token:String,
):Parcelable