package com.dearwolves.core.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dearwolves.core.model.dto.responses.MediaResponseDto

@Entity
class MediaResponse {

    @PrimaryKey
    @ColumnInfo(name = "track_id")
    var trackId: Int = 0

    @ColumnInfo(name = "track_name")
    var trackName: String = ""

    @ColumnInfo(name = "artwork_url")
    var artworkUrl100: String = ""

    @ColumnInfo(name = "track_price")
    var trackPrice: Double = 0.0

    @ColumnInfo(name = "primary_genre_name")
    var primaryGenreName: String = ""

    @ColumnInfo(name = "long_description")
    var longDescription: String = ""

    constructor(dto: MediaResponseDto) {
        this.trackId = dto.trackId
        this.trackName = dto.trackName
        this.primaryGenreName = dto.primaryGenreName
        this.trackPrice = dto.trackPrice
        this.artworkUrl100 = dto.artworkUrl100
        this.longDescription = dto.longDescription
    }

    constructor(trackName: String) {
        this.trackName = trackName
    }
}