package com.marvel.data.remote

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkErrorTransformer<T> : SingleTransformer<T, T> {

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.onErrorResumeNext(this::transformNetworkError)
    }

    private fun transformNetworkError(error: Throwable): Single<T> {
        return when (error) {

            is UnknownHostException -> {
                Single.error(NetworkError.NotConnected())
            }

            is SocketTimeoutException -> {
                Single.error(NetworkError.SlowConnection())
            }

            is IOException -> {
                if (error.cause?.message?.contentEquals("Canceled") == true) {
                    Single.error(NetworkError.Canceled())
                } else {
                    Single.error(error)
                }
            }

            else -> {
                Single.error(error)
            }
        }
    }
}