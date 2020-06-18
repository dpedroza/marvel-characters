package com.marvel.domain.core

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


abstract class UseCase<T> {

    private val disposables = CompositeDisposable()

    abstract fun buildCase(query: String, offset: Int): Single<T>

    fun execute(
        offset: Int = 0,
        query: String = "",
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