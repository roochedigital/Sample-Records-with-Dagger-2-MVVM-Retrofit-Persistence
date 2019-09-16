package com.dearwolves.core.interfaces

import com.dearwolves.core.model.database.MediaResponse
import com.dearwolves.core.model.SearchRequest
import com.dearwolves.core.interfaces.callbacks.RestRequestCallback

interface IMediaService {
    fun search(
        searchRequest: SearchRequest,
        callback: RestRequestCallback<List<MediaResponse>>
    )
}