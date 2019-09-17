package com.dearwolves.core.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dearwolves.core.model.dto.responses.MediaResponseDto

@Entity
class MediaResponse() : Parcelable {

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

    @ColumnInfo(name = "is_bookmark")
    var isBookmark: Boolean = false

    constructor(parcel: Parcel) : this() {
        trackId = parcel.readInt()
        trackName = parcel.readString()
        artworkUrl100 = parcel.readString()
        trackPrice = parcel.readDouble()
        primaryGenreName = parcel.readString()
        longDescription = parcel.readString()
        isBookmark = parcel.readInt() == 1
    }

    constructor(dto: MediaResponseDto) : this() {
        this.trackId = dto.trackId
        this.trackName = dto.trackName
        this.primaryGenreName = dto.primaryGenreName
        this.trackPrice = dto.trackPrice
        this.artworkUrl100 = dto.artworkUrl100
        this.longDescription = dto.longDescription
    }

    constructor(trackName: String) : this() {
        this.trackName = trackName
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(trackId)
        parcel.writeString(trackName)
        parcel.writeString(artworkUrl100)
        parcel.writeDouble(trackPrice)
        parcel.writeString(primaryGenreName)
        parcel.writeString(longDescription)
        parcel.writeInt(if(isBookmark )  1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MediaResponse> {
        override fun createFromParcel(parcel: Parcel): MediaResponse {
            return MediaResponse(parcel)
        }

        override fun newArray(size: Int): Array<MediaResponse?> {
            return arrayOfNulls(size)
        }
    }
}