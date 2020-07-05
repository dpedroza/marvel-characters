package com.marvel.data.model.api.comics

import com.google.gson.annotations.SerializedName
import com.marvel.data.model.api.common.Characters
import com.marvel.data.model.api.common.Creators
import com.marvel.data.model.api.common.Events
import com.marvel.data.model.api.common.Thumbnail
import com.marvel.data.model.api.common.Series
import com.marvel.data.model.api.common.Stories

data class ComicsRemoteObject(
    @SerializedName("characters")
    val characters: Characters,
    @SerializedName("collectedIssues")
    val collectedIssues: List<Any>,
    @SerializedName("collections")
    val collections: List<Any>,
    @SerializedName("creators")
    val creators: Creators,
    @SerializedName("dates")
    val dates: List<Any>,
    @SerializedName("description")
    val description: String,
    @SerializedName("diamondCode")
    val diamondCode: String,
    @SerializedName("digitalId")
    val digitalId: Int,
    @SerializedName("ean")
    val ean: String,
    @SerializedName("events")
    val events: Events,
    @SerializedName("format")
    val format: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<Any>,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("issn")
    val issn: String,
    @SerializedName("issueNumber")
    val issueNumber: Int,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("prices")
    val prices: List<Any>,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("series")
    val series: Series,
    @SerializedName("stories")
    val stories: Stories,
    @SerializedName("textObjects")
    val textObjects: List<Any>,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("title")
    val title: String,
    @SerializedName("upc")
    val upc: String,
    @SerializedName("urls")
    val urls: List<Any>,
    @SerializedName("variantDescription")
    val variantDescription: String,
    @SerializedName("variants")
    val variants: List<Any>
)
