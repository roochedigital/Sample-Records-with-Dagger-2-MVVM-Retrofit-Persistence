package com.dearwolves.samplerecords.components

import com.dearwolves.core.dagger.CoreComponent
import com.dearwolves.core.dagger.FeatureScope
import com.dearwolves.samplerecords.activities.HomeActivity
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class])
interface RecordComponent {
    fun inject(homeActivity: HomeActivity)
}