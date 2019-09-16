package com.dearwolves.core.services

import com.dearwolves.core.R
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.IRestService
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.core.model.database.MediaResponse
import com.dearwolves.core.model.SearchRequest
import com.dearwolves.core.model.dto.requests.SearchRequestDto
import com.dearwolves.core.model.dto.responses.ListResponseDto
import com.dearwolves.core.model.dto.responses.MediaResponseDto
import com.dearwolves.core.interfaces.callbacks.RestRequestCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MediaService (private val _restService: IRestService, private val _stringService: IStringService) : IMediaService {
    override fun search(
        searchRequest: SearchRequest,
        callback: RestRequestCallback<List<MediaResponse>>
    ) {
        val searchRequestDto = SearchRequestDto(searchRequest)
        val responseCall = _restService.search(searchRequestDto.term, searchRequestDto.country, searchRequestDto.media)
        responseCall.enqueue(object: Callback<ListResponseDto<MediaResponseDto>> {

            override fun onResponse(call: Call<ListResponseDto<MediaResponseDto>>, responseDto: Response<ListResponseDto<MediaResponseDto>> ) {
                if (responseDto.isSuccessful && responseDto.body() != null) {
                    val list:ArrayList<MediaResponse> =  ArrayList()
                    val resp:ListResponseDto<MediaResponseDto> = responseDto.body()!!
                    resp.results.forEach { list.add(
                        MediaResponse(
                            it
                        )
                    ) }
                    callback.onSuccess(list)
                }
                else {
                    callback.onFailure(_stringService[R.string.process_request_error])
                }
            }

            override fun onFailure(call: Call<ListResponseDto<MediaResponseDto>>, t: Throwable) {
                callback.onFailure(t.localizedMessage)
            }

        })
    }
}