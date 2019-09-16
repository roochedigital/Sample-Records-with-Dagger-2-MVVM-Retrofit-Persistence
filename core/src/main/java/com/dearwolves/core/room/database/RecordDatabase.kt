package com.dearwolves.core.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dearwolves.core.interfaces.dao.MediaResponseDao
import com.dearwolves.core.model.database.MediaResponse
import dagger.Provides
import javax.inject.Singleton

@Database(entities = [MediaResponse::class], version = 2)
abstract class RecordDatabase(val context: Context) : RoomDatabase() {

    abstract fun mediaResponseDao(): MediaResponseDao
    companion object {
        private var INSTANCE: RecordDatabase? = null

        fun getInstance(context: Context): RecordDatabase? {
            if (INSTANCE == null) {
                synchronized(RecordDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        RecordDatabase::class.java, "database.db")
                        .build()
                }
            }
            return INSTANCE as RecordDatabase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}