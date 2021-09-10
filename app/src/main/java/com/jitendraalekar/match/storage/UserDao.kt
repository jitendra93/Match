package com.jitendraalekar.match.storage

import androidx.room.*
import com.jitendraalekar.match.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun all() : Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg user: User)

    @Delete
    suspend fun delete(vararg user: User)


}