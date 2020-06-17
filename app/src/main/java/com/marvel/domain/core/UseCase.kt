package com.marvel.domain.core

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class UseCase<T> {

    private val disposables = CompositeDisposable()

    abstract fun buildCase(query: String, offset: Int): Single<T>

    fun execute(
        offset: Int,
        query: String,
        onSuccess: (value: T) -> Unit,
        onError: (t: Throwable) -> Unit = {}
    ) {
        val disposable = buildCase(query = query, offset = offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError)
        disposables.add(disposable)
    }

    fun dispose() {
        disposables.clear()
        disposables.dispose()
    }
}