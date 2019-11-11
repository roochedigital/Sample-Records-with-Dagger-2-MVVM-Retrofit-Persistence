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
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations


class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mediaService: IMediaService = mock()
    private val stringService: IStringService = mock()
    private val localRepository: LocalRepository = mock()
    private val sharedPreferenceService: ISharedPreferenceService = mock()

    private val items: ArrayList<MediaResponse> = ArrayList()

    private var date: String = "Sep 17, 9:42 am"

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        // create Sample MediaResponse
        val item = MediaResponse()
        item.trackId = 1
        item.trackName = "Test"

        // add MediaResponse to list
        items.add(item)
    }

    @Test
    fun testGetAllData() {
        // arrange
        val homeViewModel = createViewModel()

        // act
        val list = homeViewModel.getData()

        // assert
        // verify allData is called
        verify(localRepository).allData
        // expected list size is 1
        assert(list.value!!.size == 1)
    }

    @Test
    fun testSearchSuccess() {
        // arrange
        val homeViewModel = createViewModel()
        val request = SearchRequest("star", "au", "movie")
        whenever(
            mediaService.search(com.nhaarman.mockitokotlin2.any(), com.nhaarman.mockitokotlin2.any())).thenAnswer {
            (it.arguments[1] as RestRequestCallback<List<MediaResponse>>).onSuccess(items)
        }

        // act
        homeViewModel.search(request)


        // assert
        // verify insertData is called
        verify(localRepository).insertData(items)
        // expected loading value is false
        assert(!homeViewModel.loading.value!!)
    }

    @Test
    fun testSearchFailure() {
        // arrange
        val homeViewModel = createViewModel()
        val request = SearchRequest("star", "au", "movie")
        val errorMessage = "Error"
        whenever(
            mediaService.search(com.nhaarman.mockitokotlin2.any(), com.nhaarman.mockitokotlin2.any())).thenAnswer {
            (it.arguments[1] as RestRequestCallback<List<MediaResponse>>).onFailure(errorMessage)
        }

        // act
        homeViewModel.search(request)

        // assert
        // expected error value is "Error"
        assert(errorMessage == homeViewModel.error.value)
        // expected loading value is false
        assert(!homeViewModel.loading.value!!)
    }


    @Test
    fun testInit() {
        // arrange
        val homeViewModel = createViewModel()

        // act
        homeViewModel.init()

        //assert
        // check if observer is set
        assert(homeViewModel.getData().hasActiveObservers())
        // check if lastVisit is set
        assert(date == homeViewModel.lastVisit.value)
    }

    @Test
    fun testOnDestroy() {
        // arrange
        val homeViewModel = createViewModel()

        // act
        homeViewModel.init()
        homeViewModel.onDestroy()

        //assert
        // check if observer is remove
        assert(!homeViewModel.getData().hasActiveObservers())
    }


    private fun createViewModel(): HomeViewModel {
        val data: MutableLiveData<List<MediaResponse>> = MutableLiveData()
        data.value = items

        runBlocking { whenever(localRepository.allData).thenReturn(data)}
        runBlocking { whenever(sharedPreferenceService.getLastVisit()).thenReturn(date)}
        return HomeViewModel(
            mediaService,
            stringService,
            localRepository,
            sharedPreferenceService
        )
    }
}