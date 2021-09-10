package com.jitendraalekar.match.data.source.local

import com.jitendraalekar.match.data.model.ActionStatus
import com.jitendraalekar.match.data.model.User
import com.jitendraalekar.match.network.Result
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun saveUsers(vararg user: User)

    suspend fun getAllUsers() : Flow<List<User>>

    suspend fun updateUserActionStatus(uuid : String, actionStatus: ActionStatus)

    suspend fun getUserByActionStatus(actionStatus: ActionStatus) : Flow<List<User>>

    suspend fun getUserById(uuid: String) : Flow<User>


}