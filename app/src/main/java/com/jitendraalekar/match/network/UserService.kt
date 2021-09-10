package com.jitendraalekar.match.network

import com.jitendraalekar.match.network.model.APIResponse
import com.jitendraalekar.match.network.model.NetworkUser
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET(".")
    suspend fun getUsers(@Query("results") resultCount : Int = 10) : APIResponse
}