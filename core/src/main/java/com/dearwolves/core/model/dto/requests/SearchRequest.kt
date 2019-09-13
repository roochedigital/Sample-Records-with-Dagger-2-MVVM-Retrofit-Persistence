package com.dearwolves.core.model.dto.requests

import com.google.gson.annotations.SerializedName

class SearchRequest {
    @SerializedName("term")
    var term: String = ""

    @SerializedName("country")
    var country: String = ""

    @SerializedName("media")
    var media: String = ""
}