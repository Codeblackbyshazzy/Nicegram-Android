package com.appvillis.nicegram.network

import com.appvillis.nicegram.network.request.RegDateRequest
import com.appvillis.nicegram.network.response.RegDateResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface NicegramAppApi {

    @POST("v7/regdate")
    suspend fun getRegDate(@Body body: RegDateRequest): RegDateResponse
}
