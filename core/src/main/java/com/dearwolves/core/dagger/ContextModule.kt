package com.dearwolves.core.dagger

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val _context: Context) {

    @Provides
    internal fun provideApplicationContext(): Context {
        return _context
    }
}