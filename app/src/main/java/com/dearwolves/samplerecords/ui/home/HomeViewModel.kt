package com.dearwolves.samplerecords.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.model.dto.requests.SearchRequestDto
import com.dearwolves.core.model.dto.responses.ListResponseDto
import com.dearwolves.core.model.dto.responses.MediaResponseDto
import com.dearwolves.core.rest.RestRequestCallback

class HomeViewModel (private val mediaService: IMediaService): ViewModel() {

    var error = MutableLiveData<String>()
    var data = MutableLiveData<ListResponseDto<MediaResponseDto>>()

    fun search(searchRequest: SearchRequestDto) {
        mediaService.search(
            searchRequest,
            object : RestRequestCallback<ListResponseDto<MediaResponseDto>> {
                override fun onSuccess(`object`: ListResponseDto<MediaResponseDto>) {
                    data.value = `object`
                }

                override fun onFailure(errorMessage: String) {
                    error.value = errorMessage
                }
            }
        )
    }

}