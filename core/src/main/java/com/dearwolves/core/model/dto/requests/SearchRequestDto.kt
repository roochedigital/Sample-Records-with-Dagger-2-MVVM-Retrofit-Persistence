package com.dearwolves.core.model.dto.requests

import com.dearwolves.core.model.SearchRequest
import com.google.gson.annotations.SerializedName

class SearchRequestDto {
    @SerializedName("term")
    var term: String = ""

    @SerializedName("country")
    var country: String = ""

    @SerializedName("media")
    var media: String = ""


    /***
     * Default constructor as per GSON Requirement
     */
    constructor() {

    }

    constructor(request: SearchRequest) {
        this.term = request.term
        this.country = request.country
        this.media = request.media
    }
}