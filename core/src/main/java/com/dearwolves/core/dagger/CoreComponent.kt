package com.dearwolves.core.dagger

import android.content.Context
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.IRestService
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.core.room.database.RecordDatabase
import com.dearwolves.core.services.repository.MoviesRepositoryService
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, RestServiceModule::class, StringServiceModule::class, MediaServiceModule::class, DatabaseModule::class])
@Singleton
interface CoreComponent {
    fun provideApplicationContext(): Context
    fun provideRestService(): IRestService
    fun provideMediaService(): IMediaService
    fun provideStringService(): IStringService
    fun provideMoviesRepositoryService(): MoviesRepositoryService

}