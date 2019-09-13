package com.dearwolves.core.dagger

import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.IRestService
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.core.services.MediaService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MediaServiceModule {

    @Provides
    @Singleton
    internal fun provideMediaService(restService: IRestService, stringService: IStringService): IMediaService {
        return MediaService(restService, stringService)
    }
}