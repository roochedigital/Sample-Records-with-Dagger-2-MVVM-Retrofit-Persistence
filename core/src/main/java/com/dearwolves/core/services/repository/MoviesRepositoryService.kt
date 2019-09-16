package com.dearwolves.core.services.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.dearwolves.core.interfaces.dao.MediaResponseDao
import com.dearwolves.core.model.database.MediaResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MoviesRepositoryService constructor(mediaResponseDao: MediaResponseDao, compositeDisposable: CompositeDisposable) {

    var dao : MediaResponseDao = mediaResponseDao
    var comp : CompositeDisposable = compositeDisposable

    fun getAllData() : LiveData<List<MediaResponse>>{
        return LiveDataReactiveStreams.fromPublisher(dao.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.computation()))
    }

    fun insertData(student: MediaResponse){
        comp.add(Observable.fromCallable {dao.insert(student)}
            .subscribeOn(Schedulers.computation())
            .subscribe())
    }

    fun getDataById(id: String): LiveData<MediaResponse>{
        return LiveDataReactiveStreams.fromPublisher(dao.getById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.computation()))
    }

    fun deleteData(media: MediaResponse){
        comp.add(Observable.fromCallable { dao.delete(media) }
            .subscribeOn(Schedulers.computation())
            .subscribe())
    }

}