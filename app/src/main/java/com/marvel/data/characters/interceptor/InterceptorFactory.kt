package com.marvel.data.characters.interceptor

import com.marvel.BuildConfig
import com.marvel.data.characters.cryptography.Hash
import okhttp3.Interceptor

class InterceptorFactory {
    companion object {

        private const val API_KEY_QUERY_PARAMETER = "apikey"
        private const val TIMESTAMP_QUERY_PARAMETER = "ts"
        private const val HASH_QUERY_PARAMETER = "hash"

        fun create() = Interceptor {
            val timestamp = System.currentTimeMillis().toString()
            val request = it.request()
            val url = request.url()
                .newBuilder()
                .addQueryParameter(API_KEY_QUERY_PARAMETER, BuildConfig.MARVEL_PUBLIC_KEY)
                .addQueryParameter(TIMESTAMP_QUERY_PARAMETER, timestamp)
                .addQueryParameter(HASH_QUERY_PARAMETER, Hash.createHash(timestamp))
                .build()
            val newRequest = request.newBuilder()
                .url(url)
                .build()
            it.proceed(newRequest)
        }
    }
}
