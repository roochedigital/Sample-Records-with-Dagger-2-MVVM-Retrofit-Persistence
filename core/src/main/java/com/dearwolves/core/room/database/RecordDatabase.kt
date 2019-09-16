package com.dearwolves.core.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dearwolves.core.interfaces.dao.MediaResponseDao
import com.dearwolves.core.model.MediaResponse

@Database(entities = [MediaResponse::class], version = 1)
abstract class RecordDatabase: RoomDatabase() {

    abstract fun mediaResponseDao(): MediaResponseDao
}