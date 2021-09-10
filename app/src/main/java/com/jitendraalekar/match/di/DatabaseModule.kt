package com.jitendraalekar.match.di

import android.content.Context
import androidx.room.Room
import com.jitendraalekar.match.storage.MatchDatabase
import com.jitendraalekar.match.storage.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    val DB_NAME = "match_database.db"

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MatchDatabase {
        return Room.databaseBuilder(
            context,
            MatchDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(database: MatchDatabase) : UserDao {
        return database.getUserDao()
    }
}