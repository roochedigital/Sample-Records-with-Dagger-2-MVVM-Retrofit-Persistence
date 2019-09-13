package com.dearwolves.samplerecords.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.model.dto.requests.SearchRequest
import com.dearwolves.core.model.dto.responses.ListResponse
import com.dearwolves.samplerecords.R
import com.dearwolves.samplerecords.RecordApp
import com.dearwolves.samplerecords.adapters.MediaListAdapter
import com.dearwolves.samplerecords.databinding.ActivityHomeBinding
import com.dearwolves.samplerecords.viewmodels.HomeViewModel
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

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


        binding.rvMedia.layoutManager  = GridLayoutManager(this, 3)
        val mediaListAdapter = MediaListAdapter(this, ListResponse())
        binding.rvMedia.adapter = mediaListAdapter


        viewModel.data.observe(this, Observer { t ->
            mediaListAdapter.setData(t)
        })
        viewModel.error.observe(this, Observer { t -> Toast.makeText(this, t, Toast.LENGTH_LONG).show() })

        val searchRequest = SearchRequest()
        searchRequest.country = "au"
        searchRequest.term = "star"
        searchRequest.media = "movie"

        viewModel.search(searchRequest)

    }
}
