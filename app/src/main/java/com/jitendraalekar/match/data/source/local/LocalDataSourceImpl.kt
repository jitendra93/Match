package com.jitendraalekar.match.data.source.local

import com.jitendraalekar.match.data.model.ActionStatus
import com.jitendraalekar.match.data.model.User
import com.jitendraalekar.match.network.Result
import com.jitendraalekar.match.storage.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(val userDao: UserDao) : LocalDataSource {
    override suspend fun saveUsers(vararg user: User) {
        return userDao.save(*user)
    }

    override suspend fun getAllUsers(): Flow<List<User>> {
        return userDao.all()
    }

    override suspend fun updateUserActionStatus(uuid: String, actionStatus: ActionStatus) {
        userDao.updateActionStatus(uuid = uuid,actionStatus = actionStatus)
    }

    override suspend fun getUserByActionStatus(actionStatus: ActionStatus?): Flow<List<User>> {
        return userDao.findBy(actionStatus = actionStatus)
    }

    override suspend fun getUserById(uuid: String):Flow<User> {
        return userDao.find(uuid)
    }
}