package com.jitendraalekar.match.data.source.repository

import com.jitendraalekar.match.data.model.ActionStatus
import com.jitendraalekar.match.data.model.User
import com.jitendraalekar.match.data.source.NetworkUserToUserConverter
import com.jitendraalekar.match.data.source.local.LocalDataSource
import com.jitendraalekar.match.data.source.remote.RemoteDataSource
import com.jitendraalekar.match.network.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.IllegalStateException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val coroutineDispatcherIO: CoroutineDispatcher,
) : Repository {

    override suspend fun refreshData(): Result<Boolean> {
        return try {
            withContext(coroutineDispatcherIO) {
                val response = remoteDataSource.getAllUsers()
                if (response is Result.Success) {
                    localDataSource.saveUsers(
                        *response.data.results.map { NetworkUserToUserConverter.toUser(it) }
                            .toTypedArray()
                    )
                    Result.Success(true)
                } else {
                    Result.Error(IllegalStateException("Error while data fetching"))
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "response error")
            Result.Error(e)
        }
    }

    override suspend fun getUsers(): Result<Flow<List<User>>> {
        return Result.Success(localDataSource.getAllUsers())
    }

    override suspend fun saveUser(vararg user: User) {
        try {
            localDataSource.saveUsers()
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override suspend fun updateUserActionStatus(uuid: String, actionStatus: ActionStatus) {
        try {
            localDataSource.updateUserActionStatus(uuid, actionStatus)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override suspend fun getUserByActionStatus(actionStatus: ActionStatus): Result<Flow<List<User>>> {
        return try {
            Result.Success(localDataSource.getUserByActionStatus(actionStatus))
        } catch (e: Exception) {
            Timber.e(e)
            Result.Error(e)
        }
    }

    override suspend fun getUserById(uuid: String): Result<Flow<User>> {
        return try {
            Result.Success(localDataSource.getUserById(uuid))
        } catch (e: Exception) {
            Timber.e(e)
            Result.Error(e)
        }
    }


}