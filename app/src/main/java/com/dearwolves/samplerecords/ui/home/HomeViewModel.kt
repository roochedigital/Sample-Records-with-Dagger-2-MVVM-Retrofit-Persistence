package com.dearwolves.samplerecords.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.core.model.MediaResponse
import com.dearwolves.core.model.SearchRequest
import com.dearwolves.core.rest.RestRequestCallback

class HomeViewModel (private val mediaService: IMediaService, private val stringService: IStringService): ViewModel() {

    var error = MutableLiveData<String>()
    var changesNotification = MutableLiveData<Void>()
    var loading = MutableLiveData<Boolean>()
    var emptyLayoutMessage = MutableLiveData<String>()

    var data = ArrayList<MediaResponse>()

    fun init() {
        changesNotification.observeForever {
            if(data.size == 0) {
                emptyLayoutMessage.value = "There is nothing here"
            }
            else {
                emptyLayoutMessage.value = ""
            }
        }
    }


    fun search(searchRequest: SearchRequest) {
        loading.value = true
        mediaService.search(searchRequest, object : RestRequestCallback<List<MediaResponse>> {
            override fun onSuccess(`object`: List<MediaResponse>) {
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

}