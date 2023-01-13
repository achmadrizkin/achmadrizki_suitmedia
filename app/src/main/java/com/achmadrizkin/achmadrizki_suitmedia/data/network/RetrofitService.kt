package com.achmadrizkin.achmadrizki_suitmedia.data.network

import com.achmadrizkin.achmadrizki_suitmedia.data.network.network_model.ListUserResponseNetworkEntity
import com.achmadrizkin.achmadrizki_suitmedia.data.network.network_model.UserResponseNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("users")
    suspend fun getAllJobs(@Query("page") id: Int): ListUserResponseNetworkEntity
}