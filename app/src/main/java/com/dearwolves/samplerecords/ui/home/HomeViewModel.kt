package com.dearwolves.samplerecords.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.model.MediaResponse
import com.dearwolves.core.model.SearchRequest
import com.dearwolves.core.rest.RestRequestCallback

class HomeViewModel (private val mediaService: IMediaService): ViewModel() {

    var error = MutableLiveData<String>()
    var changesNotification = MutableLiveData<Void>()

    var data = ArrayList<MediaResponse>()

    fun search(searchRequest: SearchRequest) {
        mediaService.search(searchRequest, object : RestRequestCallback<List<MediaResponse>> {
            override fun onSuccess(`object`: List<MediaResponse>) {
                data.addAll(`object`)
                changesNotification.value = null
            }

            override fun onFailure(errorMessage: String) {
                error.value = errorMessage
            }
        })

    }

}