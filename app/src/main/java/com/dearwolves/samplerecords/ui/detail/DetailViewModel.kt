package com.dearwolves.samplerecords.ui.detail

import androidx.lifecycle.ViewModel
import com.dearwolves.core.model.MediaResponse
import com.dearwolves.core.repository.LocalRepository

class DetailViewModel(val mediaResponse: MediaResponse, localRepository: LocalRepository) : ViewModel() {

    init {
        localRepository.bookmarkData(mediaResponse.trackId)
    }


}