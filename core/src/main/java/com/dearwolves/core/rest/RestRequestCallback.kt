package com.dearwolves.core.rest

interface RestRequestCallback<T> {
    fun onSuccess(`object`: T)
    fun onFailure(errorMessage: String)
}