package com.jitendraalekar.match.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jitendraalekar.match.data.model.User


@Database(
    entities = [
        User::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class MatchDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
}