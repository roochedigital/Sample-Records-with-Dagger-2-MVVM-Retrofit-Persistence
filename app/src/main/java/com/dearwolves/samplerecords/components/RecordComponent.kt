package com.dearwolves.samplerecords.components

import com.dearwolves.core.dagger.CoreComponent
import com.dearwolves.core.dagger.FeatureScope
import com.dearwolves.samplerecords.ui.detail.DetailActivity
import com.dearwolves.samplerecords.ui.home.HomeActivity
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class])
interface RecordComponent {
    fun inject(homeActivity: HomeActivity)
    fun inject(detailActivity: DetailActivity)
}