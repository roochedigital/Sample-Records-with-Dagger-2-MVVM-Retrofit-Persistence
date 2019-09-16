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

    public fun insertData(mediaResponses: List<MediaResponse>) {
        Insertion(dao).execute(mediaResponses)
    }

    companion object {
        class Insertion : AsyncTask<List<MediaResponse>, Void, Void?> {
            override fun doInBackground(vararg params: List<MediaResponse>?): Void? {
                params[0]?.let {
                    it.forEach { dao.insertAll(it) }
                }
                return null
            }

            val dao:MediaResponseDao
            constructor(dao: MediaResponseDao) {
                this.dao = dao
            }


        }
    }
}
