package com.jitendraalekar.match.data.source.repository

import com.jitendraalekar.match.data.model.User
import com.jitendraalekar.match.network.Result
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getUsers() : Result<Flow<List<User>>>

    suspend fun updateUserStatus(vararg user: User)

}