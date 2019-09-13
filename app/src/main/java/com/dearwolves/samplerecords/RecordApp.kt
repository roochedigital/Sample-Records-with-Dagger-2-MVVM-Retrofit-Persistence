package com.dearwolves.samplerecords

import android.app.Application
import com.dearwolves.core.dagger.ContextModule
import com.dearwolves.core.dagger.DaggerCoreComponent
import com.dearwolves.samplerecords.components.DaggerRecordComponent
import com.dearwolves.samplerecords.components.RecordComponent

class RecordApp : Application() {

    private lateinit var _component: RecordComponent

    fun getRecordComponent(): RecordComponent {
        return _component
    }

    override fun onCreate() {
        super.onCreate()

        val coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(applicationContext))
            .build()

        _component = DaggerRecordComponent
            .builder()
            .coreComponent(coreComponent)
            .build()

    }

}