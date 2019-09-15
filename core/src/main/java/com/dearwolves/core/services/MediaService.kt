package com.dearwolves.core.services

import com.dearwolves.core.R
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.IRestService
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.core.model.dto.requests.SearchRequestDto
import com.dearwolves.core.model.dto.responses.ListResponseDto
import com.dearwolves.core.model.dto.responses.MediaResponseDto
import com.dearwolves.core.rest.RestRequestCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MediaService (private val _restService: IRestService, private val _stringService: IStringService) : IMediaService {
    override fun search(
        searchRequest: SearchRequestDto,
        callback: RestRequestCallback<ListResponseDto<MediaResponseDto>>
    ) {
        val responseCall = _restService.search(searchRequest.term, searchRequest.country, searchRequest.media)

        responseCall.enqueue(object: Callback<ListResponseDto<MediaResponseDto>> {

            override fun onResponse(
                call: Call<ListResponseDto<MediaResponseDto>>,
                responseDto: Response<ListResponseDto<MediaResponseDto>>
            ) {
                if (responseDto.body() != null) {
                    if (responseDto.isSuccessful) {
                        val resp = responseDto.body()
                        if (resp != null) {
                            callback.onSuccess(resp)
                            return
                        } else {
                            callback.onFailure(_stringService[R.string.process_request_error])
                            return
                        }
                    }
                }

                callback.onFailure(_stringService[R.string.process_request_error])
            }

            override fun onFailure(call: Call<ListResponseDto<MediaResponseDto>>, t: Throwable) {
                callback.onFailure(t.localizedMessage)
            }

        })
    }
}