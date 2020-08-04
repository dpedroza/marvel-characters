package com.marvel.presentation.schedulers

import io.reactivex.Completable
import io.reactivex.Single

fun <T> Single<T>.ioUiSchedulers(schedulerProvider: SchedulerProvider): Single<T> {
    return this
        .subscribeOn(schedulerProvider.io())
        .observeOn(schedulerProvider.ui())
}

fun Completable.ioUiSchedulers(schedulerProvider: SchedulerProvider): Completable {
    return this
        .subscribeOn(schedulerProvider.io())
        .observeOn(schedulerProvider.ui())
}
