package com.jitendraalekar.match.data.source.repository

import com.jitendraalekar.match.data.model.ActionStatus
import com.jitendraalekar.match.data.model.User
import com.jitendraalekar.match.network.Result
import kotlinx.coroutines.flow.Flow
import java.util.*

interface Repository {

    suspend fun getUsers() : Result<Flow<List<User>>>

    suspend fun saveUser(vararg user: User)

    suspend fun updateUserActionStatus(uuid : String, actionStatus: ActionStatus)

    suspend fun getUserByActionStatus(actionStatus: ActionStatus) : Result<Flow<List<User>>>

    suspend fun getUserById(uuid: String) : Result<Flow<User>>

}