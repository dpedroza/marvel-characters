package com.marvel.data.remote.service

import com.marvel.data.model.MarvelServiceApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {

    @GET("v1/public/characters")
    fun getCharacters(
        @Query("apikey") apikey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int,
        @Query("nameStartsWith") nameStartsWith: String?
    ): Single<MarvelServiceApiResponse>
}