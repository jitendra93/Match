package com.jitendraalekar.match.data.source.remote

import com.jitendraalekar.match.network.Result
import com.jitendraalekar.match.network.UserService
import com.jitendraalekar.match.network.model.APIResponse
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(val userService: UserService): RemoteDataSource {
    override suspend fun getAllUsers(): Result<APIResponse> {
        return try {
            userService.getUsers().let {
                Result.Success(it)
            }
        }catch (e : Exception){
            Result.Error(e)
        }
    }

}