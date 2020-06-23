package com.marvel.data.characters.service

import com.marvel.data.model.GetCharactersApiResponse
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
    ): Single<GetCharactersApiResponse>
}
