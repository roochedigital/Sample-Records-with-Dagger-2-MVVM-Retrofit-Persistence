package com.dearwolves.samplerecords.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.model.SearchRequest
import com.dearwolves.samplerecords.R
import com.dearwolves.samplerecords.RecordApp
import com.dearwolves.samplerecords.databinding.ActivityHomeBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    private val GridSize = 3

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


        binding.rvMedia.layoutManager  = GridLayoutManager(this, GridSize)
        val mediaListAdapter =
            MediaListAdapter(this, viewModel.data)
        binding.rvMedia.adapter = mediaListAdapter


        viewModel.changesNotification.observe(this, Observer {
            mediaListAdapter.notifyDataSetChanged()
        })
        viewModel.error.observe(this, Observer { t ->
            Snackbar.make(binding.root, t, Snackbar.LENGTH_LONG).show()
        })

        val searchRequest = SearchRequest("star", "au", "movie")
        viewModel.search(searchRequest)

    }
}
