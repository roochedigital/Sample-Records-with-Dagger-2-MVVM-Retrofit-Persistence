package com.dearwolves.core.interfaces.callbacks

interface RestRequestCallback<T> {
    fun onSuccess(`object`: T)
    fun onFailure(errorMessage: String)
}