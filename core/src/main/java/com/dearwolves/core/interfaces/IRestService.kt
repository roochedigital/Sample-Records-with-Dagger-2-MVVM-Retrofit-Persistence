package com.dearwolves.core.interfaces

import com.dearwolves.core.model.dto.responses.ListResponseDto
import com.dearwolves.core.model.dto.responses.MediaResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRestService {

    @GET("search")
    fun search(
        @Query("term") term: String, @Query("country") country: String, @Query("media") media: String
    ): Call<ListResponseDto<MediaResponseDto>>
}