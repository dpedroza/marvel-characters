package com.marvel.data.remote

import io.reactivex.Single

fun <T> Single<T>.composeErrorTransformers(): Single<T> {
    return this.compose(NetworkErrorTransformer())
}