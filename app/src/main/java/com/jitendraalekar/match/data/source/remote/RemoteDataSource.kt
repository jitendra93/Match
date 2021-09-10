package com.jitendraalekar.match.data.source.remote

import com.jitendraalekar.match.network.Result
import com.jitendraalekar.match.network.model.APIResponse

interface RemoteDataSource {
    suspend fun getAllUsers() : Result<APIResponse>
}