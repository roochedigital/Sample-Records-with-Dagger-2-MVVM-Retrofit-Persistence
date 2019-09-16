package com.dearwolves.samplerecords.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.IOnItemSelected
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.core.model.database.MediaResponse
import com.dearwolves.core.model.SearchRequest
import com.dearwolves.core.services.repository.MoviesRepositoryService
import com.dearwolves.samplerecords.R
import com.dearwolves.samplerecords.RecordApp
import com.dearwolves.samplerecords.databinding.ActivityHomeBinding
import com.dearwolves.samplerecords.ui.detail.DetailActivity
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), IOnItemSelected<MediaResponse> {

    private val GridSize = 3

    @Inject
    lateinit var mediaService: IMediaService

    @Inject
    lateinit var stringService: IStringService

    @Inject
    lateinit var moviesRepositoryService: MoviesRepositoryService

    private lateinit var viewModel: HomeViewModel
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as RecordApp)
            .getRecordComponent()
            .inject(this@HomeActivity)

        viewModel = HomeViewModel(mediaService, stringService, moviesRepositoryService)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        binding.rvMedia.layoutManager  = GridLayoutManager(this, GridSize)
        val mediaListAdapter =
            MediaListAdapter(this, viewModel.data, this)
        binding.rvMedia.adapter = mediaListAdapter


        viewModel.changesNotification.observe(this, Observer {
            mediaListAdapter.notifyDataSetChanged()
        })
        viewModel.error.observe(this, Observer { t ->
            Snackbar.make(binding.root, t, Snackbar.LENGTH_LONG).show()
        })

        viewModel.init()
        viewModel.search(SearchRequest("star", "au", "movie"))

    }

    override fun onSelected(item: MediaResponse) {
        val intent = Intent(this, DetailActivity::class.java)
        //intent.putExtra(DetailActivity.Companion.Item, item)
        startActivity(intent)

    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }
}
