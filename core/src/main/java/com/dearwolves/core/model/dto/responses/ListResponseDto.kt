package com.dearwolves.core.model.dto.responses

import com.google.gson.annotations.SerializedName

class ListResponseDto<T> {
    @SerializedName("count")
    var resultCount: Int = 0

    @SerializedName("results")
    var results: List<T> = arrayListOf()

}