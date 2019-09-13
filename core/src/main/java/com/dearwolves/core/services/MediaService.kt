package com.dearwolves.core.services

import com.dearwolves.core.R
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.IRestService
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.core.model.dto.requests.SearchRequest
import com.dearwolves.core.model.dto.responses.ListResponse
import com.dearwolves.core.model.dto.responses.MediaResponse
import com.dearwolves.core.rest.RestRequestCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MediaService (private val _restService: IRestService, private val _stringService: IStringService) : IMediaService {
    override fun search(
        searchRequest: SearchRequest,
        callback: RestRequestCallback<ListResponse<MediaResponse>>
    ) {
        val responseCall = _restService.search(searchRequest.term, searchRequest.country, searchRequest.media)

        responseCall.enqueue(object: Callback<ListResponse<MediaResponse>> {

            override fun onResponse(
                call: Call<ListResponse<MediaResponse>>,
                response: Response<ListResponse<MediaResponse>>
            ) {
                if (response.body() != null) {
                    if (response.isSuccessful) {
                        val resp = response.body()
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

            override fun onFailure(call: Call<ListResponse<MediaResponse>>, t: Throwable) {
                callback.onFailure(t.localizedMessage)
            }

        })
    }
}