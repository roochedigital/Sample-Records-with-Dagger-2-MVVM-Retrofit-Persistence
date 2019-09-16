package com.dearwolves.core.dagger

import android.content.Context
import androidx.room.Room
import com.dearwolves.core.room.database.RecordDatabase
import com.dearwolves.core.services.repository.MoviesRepositoryService
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides @Singleton fun provideStudentDatabase(context: Context): RecordDatabase {
        return Room.databaseBuilder(context,
            RecordDatabase::class.java, "media.db")
            .build()
    }

    @Provides @Singleton fun provideMediaDao(recordDatabase: RecordDatabase) = recordDatabase.mediaResponseDao()

    @Provides @Singleton fun provideCompositeDisposable() = CompositeDisposable()


}