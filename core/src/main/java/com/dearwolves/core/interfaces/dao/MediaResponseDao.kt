package com.dearwolves.core.interfaces.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dearwolves.core.model.MediaResponse

@Dao
interface MediaResponseDao {

    @Query("SELECT * FROM mediaresponse")
    fun getAll(): List<MediaResponse>

    @Insert
    fun insertAll(vararg todo: MediaResponse)

}
