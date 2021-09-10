package com.jitendraalekar.match.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jitendraalekar.match.data.model.ActionAdapter
import com.jitendraalekar.match.data.model.User


@Database(
    entities = [
        User::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(ActionAdapter::class)
abstract class MatchDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
}