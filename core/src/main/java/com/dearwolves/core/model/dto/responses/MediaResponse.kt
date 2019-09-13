package com.dearwolves.core.model.dto.responses

import java.util.*

class MediaResponse {
    var wrapperType: String = ""
    var kind: String = ""
    var collectionId: Int = 0
    var trackId: Int = 0
    var artistName: String = ""
    var collectionName: String = ""
    var trackName: String = ""
    var collectionCensoredName: String = ""
    var trackCensoredName: String = ""
    var collectionArtistId: Int = 0
    var collectionArtistViewUrl: String = ""
    var collectionViewUrl: String = ""
    var trackViewUrl: String = ""
    var previewUrl: String = ""
    var artworkUrl30: String = ""
    var artworkUrl60: String = ""
    var artworkUrl100: String = ""
    var collectionPrice: String = ""
    var trackPrice: Double = 0.0
    var trackRentalPrice: Double = 0.0
    var collectionHdPrice: Double = 0.0
    var trackHdPrice: Double = 0.0
    var trackHdRentalPrice: Double = 0.0
    var releaseDate: Date? = null
    var collectionExplicitness: String = ""
    var trackExplicitness: String = ""
    var discCount: Int = 0
    var discNumber: Int = 0
    var trackCount: Int = 0
    var trackNumber: Int = 0
    var trackTimeMillis: Int = 0
    var country: String = ""
    var currency: String = ""
    var primaryGenreName: String = ""
    var contentAdvisoryRating: String = ""
    var shortDescription: String = ""
    var longDescription: String = ""
    var hasITunesExtras: Boolean = false
}