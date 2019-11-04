package com.dearwolves.samplerecords

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.ISharedPreferenceService
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.core.model.MediaResponse
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


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetAllData() {
        val homeViewModel = createViewModel()

        val list = homeViewModel.getData()

        verify(localRepository).allData

        assert(list.value!!.size == 1)
    }

    private fun createViewModel(): HomeViewModel {
        val item = MediaResponse()
        item.trackId = 1
        item.trackName = "Test"

        val list:ArrayList<MediaResponse> = ArrayList()
        list.add(item)

        val data: MutableLiveData<List<MediaResponse>> = MutableLiveData()
        data.value = list

        runBlocking { whenever(localRepository.allData).thenReturn(data) }
        return HomeViewModel(
            mediaService,
            stringService,
            localRepository,
            sharedPreferenceService
        )
    }
}