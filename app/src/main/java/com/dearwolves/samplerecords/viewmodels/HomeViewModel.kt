package com.dearwolves.samplerecords.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.model.dto.requests.SearchRequest
import com.dearwolves.core.model.dto.responses.ListResponse
import com.dearwolves.core.model.dto.responses.MediaResponse
import com.dearwolves.core.rest.RestRequestCallback

class HomeViewModel (private val mediaService: IMediaService): ViewModel() {

    var error = MutableLiveData<String>()
    var data = MutableLiveData<ListResponse<MediaResponse>>()

    fun search(searchRequest: SearchRequest) {
        mediaService.search(
            searchRequest,
            object : RestRequestCallback<ListResponse<MediaResponse>> {
                override fun onSuccess(`object`: ListResponse<MediaResponse>) {
                    data.value = `object`
                }

                override fun onFailure(errorMessage: String) {
                    error.value = errorMessage

                }
            }
        )
    }

}