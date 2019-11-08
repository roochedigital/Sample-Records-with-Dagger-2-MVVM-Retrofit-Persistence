package com.dearwolves.samplerecords

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.ISharedPreferenceService
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.core.interfaces.callbacks.RestRequestCallback
import com.dearwolves.core.model.MediaResponse
import com.dearwolves.core.model.SearchRequest
import com.dearwolves.core.repository.LocalRepository
import com.dearwolves.samplerecords.ui.home.HomeViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mediaService: IMediaService = mock()
    private val stringService: IStringService = mock()
    private val localRepository: LocalRepository = mock()
    private val sharedPreferenceService: ISharedPreferenceService = mock()

    private val items :ArrayList<MediaResponse> = ArrayList()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val item = MediaResponse()
        item.trackId = 1
        item.trackName = "Test"

        items.add(item)
    }

    @Test
    fun testGetAllData() {
        val homeViewModel = createViewModel()

        val list = homeViewModel.getData()

        verify(localRepository).allData

        assert(list.value!!.size == 1)
    }

    @Test
    fun testSearchSuccess() {
        val homeViewModel = createViewModel()

        whenever(
            mediaService.search(com.nhaarman.mockitokotlin2.any(), com.nhaarman.mockitokotlin2.any())).thenAnswer {
            (it.arguments[1] as RestRequestCallback<List<MediaResponse>>).onSuccess(items)
        }

        homeViewModel.search(SearchRequest("star", "au", "movie"))

        verify(localRepository).insertData(items)
        assert(!homeViewModel.loading.value!!)
    }

    @Test
    fun testSearchFailure() {
        val homeViewModel = createViewModel()

        whenever(
            mediaService.search(com.nhaarman.mockitokotlin2.any(), com.nhaarman.mockitokotlin2.any())).thenAnswer {
            (it.arguments[1] as RestRequestCallback<List<MediaResponse>>).onFailure("Error")
        }

        homeViewModel.search(SearchRequest("star", "au", "movie"))

        assert("Error" == homeViewModel.error.value)
        assert(!homeViewModel.loading.value!!)
    }

    private fun createViewModel(): HomeViewModel {
        val data: MutableLiveData<List<MediaResponse>> = MutableLiveData()
        data.value = items

        runBlocking { whenever(localRepository.allData).thenReturn(data)}
        return HomeViewModel(
            mediaService,
            stringService,
            localRepository,
            sharedPreferenceService
        )
    }
}