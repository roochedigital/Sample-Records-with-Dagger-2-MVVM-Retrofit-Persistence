package com.dearwolves.core.interfaces

import com.dearwolves.core.model.dto.requests.SearchRequestDto
import com.dearwolves.core.model.dto.responses.ListResponseDto
import com.dearwolves.core.model.dto.responses.MediaResponseDto
import com.dearwolves.core.rest.RestRequestCallback

interface IMediaService {
    fun search(
        searchRequest: SearchRequestDto,
        callback: RestRequestCallback<ListResponseDto<MediaResponseDto>>
    )
}