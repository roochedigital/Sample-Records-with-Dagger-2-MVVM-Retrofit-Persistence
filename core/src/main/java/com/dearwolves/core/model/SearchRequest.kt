package com.dearwolves.core.model

class SearchRequest {

    var term: String = ""
    var country: String = ""
    var media: String = ""

    constructor(term: String, country: String, media:String) {
        this.term = term
        this.country = country
        this.media = media
    }


}