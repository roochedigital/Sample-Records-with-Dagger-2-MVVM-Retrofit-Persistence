package com.dearwolves.samplerecords.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.core.interfaces.callbacks.RestRequestCallback
import com.dearwolves.core.model.database.MediaResponse
import com.dearwolves.core.model.SearchRequest
import com.dearwolves.core.services.repository.MoviesRepositoryService

class HomeViewModel (private val mediaService: IMediaService, private val stringService: IStringService, private val moviesRepositoryService: MoviesRepositoryService): ViewModel() {
    var data = ArrayList<MediaResponse>()

    var error = MutableLiveData<String>()
    var changesNotification = MutableLiveData<Void>()
    var loading = MutableLiveData<Boolean>()
    var emptyDisplayMessage = MutableLiveData<String>()


    private val changeNotificationObserver = Observer<Void> {
        if(data.size == 0) {
            emptyDisplayMessage.value = "There is nothing here"
        }
        else {
            emptyDisplayMessage.value = ""
        }
    }

    fun init() {
        changesNotification.observeForever(changeNotificationObserver)
    }


    fun search(searchRequest: SearchRequest) {
        loading.value = true
        mediaService.search(searchRequest, object : RestRequestCallback<List<MediaResponse>> {
            override fun onSuccess(`object`: List<MediaResponse>) {
                moviesRepositoryService.insertData(`object`)
                data.addAll(`object`)
                changesNotification.value = null
                loading.value = false
            }

            override fun onFailure(errorMessage: String) {
                error.value = errorMessage
                loading.value = false
            }
        })

    }


    fun onDestroy() {
        changesNotification.removeObserver(changeNotificationObserver)
    }

}