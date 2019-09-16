package com.dearwolves.core.model

import com.dearwolves.core.model.dto.responses.MediaResponseDto

class MediaResponse(dto: MediaResponseDto) {
    var trackName: String = ""
    var artworkUrl100: String = ""
    var trackPrice: Double = 0.0
    var primaryGenreName: String = ""

    init {
        this.trackName = dto.trackName
        this.primaryGenreName = dto.primaryGenreName
        this.trackPrice = dto.trackPrice
        this.artworkUrl100 = dto.artworkUrl100
    }
}