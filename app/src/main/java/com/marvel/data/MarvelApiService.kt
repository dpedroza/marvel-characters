package com.marvel.data

import com.marvel.data.model.CharactersResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {

    @GET("v1/public/characters")
    fun getCharacters(
        @Query("apikey") apikey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String
    ): Single<CharactersResponse>
}