package com.jitendraalekar.match.data.source.repository

import android.content.SharedPreferences
import com.jitendraalekar.match.data.model.User
import com.jitendraalekar.match.data.source.NetworkUserToUserConverter
import com.jitendraalekar.match.data.source.local.LocalDataSource
import com.jitendraalekar.match.data.source.remote.RemoteDataSource
import com.jitendraalekar.match.network.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val coroutineDispatcherIO: CoroutineDispatcher,
) : Repository {

    override suspend fun getUsers(): Result<Flow<List<User>>> {
        return  try {
            withContext(coroutineDispatcherIO) {
                val response = remoteDataSource.getAllUsers()
               if(response is Result.Success){
                   response.data.results.map{NetworkUserToUserConverter.toUser(it)}.forEach {
                       localDataSource.insertUsers(it)
                   }
               }
                Result.Success(localDataSource.getAllUsers())
            }
        }catch (e : Exception){
            Result.Error(e)
        }
    }

    override suspend fun updateUserStatus(vararg user: User) {
        localDataSource.insertUsers(*user)
    }


}