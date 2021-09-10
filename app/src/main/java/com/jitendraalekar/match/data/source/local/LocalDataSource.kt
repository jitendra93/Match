package com.jitendraalekar.match.data.source.local

import com.jitendraalekar.match.data.model.User
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertUsers(vararg user: User)

    suspend fun getAllUsers() : Flow<List<User>>
}