package com.dearwolves.samplerecords.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dearwolves.core.interfaces.IOnComplete
import com.dearwolves.core.model.MediaResponse
import com.dearwolves.core.repository.LocalRepository

class DetailViewModel(val mediaResponse: MediaResponse, val localRepository: LocalRepository) : ViewModel() {

    var isBookmarked = MutableLiveData<Boolean>()

    fun init() {
        isBookmarked.value = mediaResponse.isBookmark
    }

    fun onBookMarkToggle() {
        mediaResponse.isBookmark = !mediaResponse.isBookmark
        localRepository.updateItem(mediaResponse,
            object : IOnComplete {
                override fun onComplete() {
                    isBookmarked.value = mediaResponse.isBookmark
                }
            })
    }

}