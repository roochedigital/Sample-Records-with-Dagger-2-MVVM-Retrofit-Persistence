package com.dearwolves.samplerecords.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.samplerecords.R
import com.dearwolves.samplerecords.RecordApp
import com.dearwolves.samplerecords.databinding.ActivityMovieDetailsBinding
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var mediaService: IMediaService

    @Inject
    lateinit var stringService: IStringService

    private lateinit var viewModel: DetailViewModel
    lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as RecordApp)
            .getRecordComponent()
            .inject(this@DetailActivity)

        viewModel = DetailViewModel()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding.lifecycleOwner = this
    }
}
