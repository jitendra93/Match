package com.jitendraalekar.match.data.source.local

import com.jitendraalekar.match.data.model.User
import com.jitendraalekar.match.storage.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(val userDao: UserDao) : LocalDataSource {
    override suspend fun insertUsers(vararg user: User) {
        return userDao.save(*user)
    }

    override suspend fun getAllUsers(): Flow<List<User>> {
        return userDao.all()
    }
}