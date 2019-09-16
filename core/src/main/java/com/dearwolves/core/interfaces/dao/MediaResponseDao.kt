package com.dearwolves.core.interfaces.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dearwolves.core.model.MediaResponse

@Dao
interface MediaResponseDao {

    @Query("SELECT * FROM mediaresponse")
    fun getAll(): LiveData<List<MediaResponse>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg todo: MediaResponse)

    @Query("UPDATE mediaresponse SET is_bookmark = 0")
    fun setBookmarkFalse()

    @Query("UPDATE mediaresponse SET is_bookmark = 1 WHERE track_id = :trackId")
    fun setBookmarkTrue(trackId: Int = 0)

    @Query("SELECT * FROM mediaresponse WHERE is_bookmark = 1")
    fun getBookMark(): MediaResponse

}
