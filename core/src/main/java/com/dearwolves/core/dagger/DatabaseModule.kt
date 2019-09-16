package com.dearwolves.core.dagger

import android.content.Context
import androidx.room.Room
import com.dearwolves.core.repository.LocalRepository
import com.dearwolves.core.room.database.RecordDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): RecordDatabase {
        return Room.databaseBuilder(
            context,
            RecordDatabase::class.java, "database-name"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalRepository(recordDatabase: RecordDatabase) = LocalRepository(recordDatabase)

}