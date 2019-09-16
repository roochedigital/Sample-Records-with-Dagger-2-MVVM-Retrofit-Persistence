package com.dearwolves.core.dagger

import android.content.Context
import com.dearwolves.core.interfaces.ISharedPreferenceService
import com.dearwolves.core.services.SharedPreferenceService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferenceModule {

    @Provides
    @Singleton
    internal fun provideSharedPreferenceService(context: Context): ISharedPreferenceService {
        return SharedPreferenceService(context)
    }
}