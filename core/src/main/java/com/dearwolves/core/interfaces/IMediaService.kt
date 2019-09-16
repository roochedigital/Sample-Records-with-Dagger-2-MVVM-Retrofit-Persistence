package com.dearwolves.core.interfaces

import com.dearwolves.core.model.MediaResponse
import com.dearwolves.core.model.SearchRequest
import com.dearwolves.core.rest.RestRequestCallback

interface IMediaService {
    fun search(
        searchRequest: SearchRequest,
        callback: RestRequestCallback<List<MediaResponse>>
    )
}