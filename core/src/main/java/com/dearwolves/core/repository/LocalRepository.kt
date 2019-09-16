package com.dearwolves.core.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.dearwolves.core.interfaces.dao.MediaResponseDao
import com.dearwolves.core.model.MediaResponse
import com.dearwolves.core.room.database.RecordDatabase

class LocalRepository(recordDatabase: RecordDatabase) {

    val dao:MediaResponseDao
    val allData: LiveData<List<MediaResponse>>

    init {
        dao = recordDatabase.mediaResponseDao()
        allData = dao.getAll()
    }

    fun insertData(mediaResponses: List<MediaResponse>) {
        Insertion(dao).execute(mediaResponses)
    }

    fun bookmarkData(trackId: Int) {
        Bookmark(dao).execute(trackId)
    }

    fun getBookmark(): MediaResponse? {
        return GetBookmark(dao).execute().get()
    }

    companion object {
        class Insertion(private val dao: MediaResponseDao) : AsyncTask<List<MediaResponse>, Void, Void?>() {
            override fun doInBackground(vararg params: List<MediaResponse>?): Void? {
                params[0]?.let {
                    it.forEach { dao.insertAll(it) }
                }
                return null
            }

        }

        class Bookmark(private val dao: MediaResponseDao) : AsyncTask<Int, Void, Void?>() {
            override fun doInBackground(vararg params: Int?): Void? {
                params[0]?.let {
                    dao.setBookmarkFalse()
                    dao.setBookmarkTrue(it)
                }
                return null
            }
        }

        class GetBookmark(private val dao: MediaResponseDao) : AsyncTask<Void, Void, MediaResponse?>() {
            override fun doInBackground(vararg params: Void?): MediaResponse? {
                return dao.getBookMark()
            }

        }


    }
}
