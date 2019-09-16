package com.dearwolves.samplerecords.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.transition.Visibility
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.util.StringUtil
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.IOnItemSelected
import com.dearwolves.core.interfaces.ISharedPreferenceService
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.core.model.MediaResponse
import com.dearwolves.core.model.SearchRequest
import com.dearwolves.core.repository.LocalRepository
import com.dearwolves.samplerecords.R
import com.dearwolves.samplerecords.RecordApp
import com.dearwolves.samplerecords.databinding.ActivityHomeBinding
import com.dearwolves.samplerecords.ui.detail.DetailActivity
import com.google.android.material.snackbar.Snackbar
import java.util.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), IOnItemSelected<MediaResponse> {

    private val GRID_SIZE = 3

    @Inject
    lateinit var mediaService: IMediaService

    @Inject
    lateinit var stringService: IStringService

    @Inject
    lateinit var localRepository: LocalRepository

    @Inject
    lateinit var sharedPreferenceService: ISharedPreferenceService

    private lateinit var viewModel: HomeViewModel
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as RecordApp)
            .getRecordComponent()
            .inject(this@HomeActivity)

        viewModel = HomeViewModel(mediaService, stringService, localRepository, sharedPreferenceService)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        if(viewModel.getBookmark() != null) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.ITEM, viewModel.getBookmark())
            startActivity(intent)
        }

        binding.rvMedia.layoutManager  = GridLayoutManager(this, GRID_SIZE)
        val mediaListAdapter =
            MediaListAdapter(this, localRepository.allData, this)
        binding.rvMedia.adapter = mediaListAdapter


        viewModel.getData().observe(this, Observer { mediaListAdapter.notifyDataSetChanged() })

        viewModel.error.observe(this, Observer { t ->
            Snackbar.make(binding.root, t, Snackbar.LENGTH_LONG).show()
        })

        viewModel.lastVisit.observe(this, Observer { t ->
            binding.lastVisit.visibility = if(TextUtils.isEmpty(t)) View.GONE else View.VISIBLE

            val sb = StringBuilder()
            sb.append(stringService.get(R.string.last_visit)).append(sharedPreferenceService.getLastVisit())

            binding.lastVisit.text = sb.toString()
        })

        viewModel.init()
        viewModel.search(SearchRequest("star", "au", "movie"))
    }

    override fun onSelected(item: MediaResponse) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.ITEM, item)
        startActivity(intent)
    }

    override fun onDestroy() {
        sharedPreferenceService.saveLastVisit(Date())
        viewModel.onDestroy()
        super.onDestroy()
    }
}
