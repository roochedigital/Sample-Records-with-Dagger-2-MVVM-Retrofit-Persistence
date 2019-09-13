package com.dearwolves.core.interfaces

import com.dearwolves.core.model.dto.requests.SearchRequest
import com.dearwolves.core.model.dto.responses.ListResponse
import com.dearwolves.core.model.dto.responses.MediaResponse
import com.dearwolves.core.rest.RestRequestCallback

interface IMediaService {
    fun search(
        searchRequest: SearchRequest,
        callback: RestRequestCallback<ListResponse<MediaResponse>>
    )
}