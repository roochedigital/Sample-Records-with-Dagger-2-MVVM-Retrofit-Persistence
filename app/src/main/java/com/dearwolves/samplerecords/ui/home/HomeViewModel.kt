package com.dearwolves.samplerecords.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.ISharedPreferenceService
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.core.interfaces.callbacks.RestRequestCallback
import com.dearwolves.core.model.MediaResponse
import com.dearwolves.core.model.SearchRequest
import com.dearwolves.core.repository.LocalRepository
import com.dearwolves.samplerecords.R

class HomeViewModel (private val mediaService: IMediaService, private val stringService: IStringService, private val localRepository: LocalRepository, private val sharedPreferenceService: ISharedPreferenceService): ViewModel() {

    var error = MutableLiveData<String>()
    var loading = MutableLiveData<Boolean>()
    var lastVisit = MutableLiveData<String>()
    var emptyDisplayMessage = MutableLiveData<String>()


    private val changeNotificationObserver = Observer<List<MediaResponse>> {
        if(it.isEmpty()) {
            emptyDisplayMessage.value = stringService.get(R.string.nothing_here)
        }
        else {
            emptyDisplayMessage.value = ""
        }
    }

    fun init() {
        getData().observeForever(changeNotificationObserver)
        lastVisit.value = sharedPreferenceService.getLastVisit()
    }


    fun search(searchRequest: SearchRequest) {
        loading.value = true
        mediaService.search(searchRequest, object : RestRequestCallback<List<MediaResponse>> {
            override fun onSuccess(`object`: List<MediaResponse>) {
                localRepository.insertData(`object`)
                loading.value = false
            }

            override fun onFailure(errorMessage: String) {
                error.value = errorMessage
                loading.value = false
            }
        })
    }

    fun onDestroy() {
        getData().removeObserver(changeNotificationObserver)
    }

    fun getData(): LiveData<List<MediaResponse>> {
        return localRepository.allData
    }

}