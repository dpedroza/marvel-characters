package com.marvel.data

import com.marvel.data.model.GetCharacthersResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface MarvelApiService {

    @GET("v1/public/characters")
    fun getCharacters(
        @Query("name") name: String,
        @Query("nameStartsWith") nameStartsWith: String,
        @Query("modifiedSince") modifiedSince: Date,
        @Query("comics") comics: String,
        @Query("series") series: Int,
        @Query("events") events: Int,
        @Query("stories") stories: Int,
        @Query("orderBy") orderBy: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 5
    ): Single<GetCharacthersResponse>
}