package com.dearwolves.core.dagger

import android.content.Context
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.IRestService
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, RestServiceModule::class, StringServiceModule::class, MediaServiceModule::class])
@Singleton
interface CoreComponent {
    fun provideApplicationContext(): Context
    fun provideRestService(): IRestService
    fun provideMediaService(): IMediaService

}