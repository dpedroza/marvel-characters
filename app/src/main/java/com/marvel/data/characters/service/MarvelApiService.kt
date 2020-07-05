package com.marvel.data.characters.service

import com.marvel.data.characters.model.characters.GetCharactersApiResponse
import com.marvel.data.characters.model.comics.GetComicsApiResponse
import com.marvel.data.characters.model.series.GetSeriesApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("v1/public/characters/{characterId}/comics")
    fun getComics(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int
    ): Single<GetComicsApiResponse>

    @GET("v1/public/characters/{characterId}/series")
    fun getSeries(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int
    ): Single<GetSeriesApiResponse>
}
