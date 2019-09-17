package com.dearwolves.core.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.dearwolves.core.interfaces.IOnComplete
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

    fun getItem(trackId: Int) : LiveData<MediaResponse> {
        return dao.get(trackId)
    }

    fun insertData(mediaResponses: List<MediaResponse>) {
        Insertion(dao).execute(mediaResponses)
    }

    fun updateItem(mediaResponse: MediaResponse, onComplete: IOnComplete) {
        UpdateItem(dao, onComplete).execute(mediaResponse)
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

        class UpdateItem(private val dao: MediaResponseDao , private val onComplete: IOnComplete) : AsyncTask<MediaResponse, Void, Void?>() {
            override fun doInBackground(vararg params: MediaResponse): Void? {
                params[0].let {
                    dao.updateItem(it)
                }
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                onComplete.onComplete()
            }


        }

    }
}
