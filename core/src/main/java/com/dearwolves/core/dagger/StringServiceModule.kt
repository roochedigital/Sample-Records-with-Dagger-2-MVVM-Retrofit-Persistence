package com.dearwolves.core.dagger

import android.content.Context
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.core.services.StringService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StringServiceModule {

    @Provides
    @Singleton
    internal fun provideStringService(context: Context): IStringService {
        return StringService(context)
    }

}