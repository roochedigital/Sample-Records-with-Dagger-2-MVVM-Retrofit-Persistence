package com.dearwolves.core.interfaces.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.dearwolves.core.model.database.MediaResponse
import io.reactivex.Flowable

@Dao
interface MediaResponseDao {

    @Query("SELECT * from Media")
    fun getAll(): Flowable<List<MediaResponse>>

    @Query("SELECT * FROM Media WHERE track_id = :id ")
    fun getById(id: String): Flowable<MediaResponse>

    @Insert(onConflict = REPLACE)
    fun insert(media: MediaResponse)

    @Delete
    fun delete(student: MediaResponse)

}

