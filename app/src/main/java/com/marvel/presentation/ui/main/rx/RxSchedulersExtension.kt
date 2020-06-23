package com.marvel.presentation.ui.main.rx

import io.reactivex.Completable
import io.reactivex.Single


fun <T> Single<T>.ioUiSchedulers(schedulerProvider: SchedulerProvider): Single<T> {
    return this
        .subscribeOn(schedulerProvider.io())
        .observeOn(schedulerProvider.ui())
}

fun Completable.ioComputationSchedulers(schedulerProvider: SchedulerProvider): Completable {
    return this
        .subscribeOn(schedulerProvider.computation())
        .observeOn(schedulerProvider.ui())
}