package com.jitendraalekar.match.storage

import androidx.room.*
import com.jitendraalekar.match.data.model.ActionStatus
import com.jitendraalekar.match.data.model.User
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun all() : Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg user: User)

    @Query("SELECT * FROM user WHERE uuid = :id")
     fun find(id : String) : Flow<User>

    @Query("SELECT * FROM user WHERE actionStatus = :actionStatus")
     fun findBy(actionStatus: ActionStatus?) : Flow<List<User>>

    @Query("UPDATE user SET actionStatus = :actionStatus where uuid = :uuid")
    suspend fun updateActionStatus(uuid: String, actionStatus: ActionStatus)


}