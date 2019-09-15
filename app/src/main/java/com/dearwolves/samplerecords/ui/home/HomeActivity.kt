package com.dearwolves.samplerecords.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.model.SearchRequest
import com.dearwolves.core.model.dto.requests.SearchRequestDto
import com.dearwolves.core.model.dto.responses.ListResponseDto
import com.dearwolves.samplerecords.R
import com.dearwolves.samplerecords.RecordApp
import com.dearwolves.samplerecords.databinding.ActivityHomeBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    private val Grid_Size = 3

    @Inject
    lateinit var mediaService: IMediaService

    private lateinit var viewModel: HomeViewModel
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as RecordApp)
            .getRecordComponent()
            .inject(this@HomeActivity)

        viewModel = HomeViewModel(mediaService)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        binding.rvMedia.layoutManager  = GridLayoutManager(this, Grid_Size)
        val mediaListAdapter =
            MediaListAdapter(this, ListResponseDto())
        binding.rvMedia.adapter = mediaListAdapter


        viewModel.data.observe(this, Observer { t ->
            mediaListAdapter.setData(t)
        })
        viewModel.error.observe(this, Observer { t ->
            Snackbar.make(binding.root, t, Snackbar.LENGTH_LONG).show()
        })

        val searchRequest = SearchRequest("star", "movie", "au")
        viewModel.search(SearchRequestDto(searchRequest))

    }
}
