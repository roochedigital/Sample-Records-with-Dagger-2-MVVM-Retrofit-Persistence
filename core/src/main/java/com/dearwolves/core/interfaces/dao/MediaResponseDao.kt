package com.dearwolves.core.interfaces.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.dearwolves.core.model.MediaResponse

@Dao
interface MediaResponseDao {

    @Query("SELECT * FROM mediaresponse")
    fun getAll(): LiveData<List<MediaResponse>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg todo: MediaResponse)

    @Query("SELECT * FROM mediaresponse WHERE track_id = :trackId")
    fun get(trackId: Int): LiveData<MediaResponse>

    @Update(onConflict = REPLACE)
    fun updateItem(mediaResponse: MediaResponse)

}
