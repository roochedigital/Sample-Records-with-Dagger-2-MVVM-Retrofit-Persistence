package com.dearwolves.core.interfaces.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dearwolves.core.model.MediaResponse

@Dao
interface MediaResponseDao {

    @Query("SELECT * FROM mediaresponse")
    fun getAll(): LiveData<List<MediaResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo: MediaResponse)

}
