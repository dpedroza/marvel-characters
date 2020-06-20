package com.marvel.data.characters.error

import io.reactivex.Single

fun <T> Single<T>.composeErrorTransformers(): Single<T> {
    return this.compose(NetworkErrorTransformer())
}