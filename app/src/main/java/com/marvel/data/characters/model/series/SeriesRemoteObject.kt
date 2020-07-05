package com.marvel.data.characters.model.series

import com.google.gson.annotations.SerializedName
import com.marvel.data.characters.model.common.Previous
import com.marvel.data.characters.model.common.Next
import com.marvel.data.characters.model.common.Characters
import com.marvel.data.characters.model.common.Comics
import com.marvel.data.characters.model.common.Creators
import com.marvel.data.characters.model.common.Events
import com.marvel.data.characters.model.common.Stories
import com.marvel.data.characters.model.common.Thumbnail

data class SeriesRemoteObject(
    @SerializedName("characters")
    val characters: Characters,
    @SerializedName("comics")
    val comics: Comics,
    @SerializedName("creators")
    val creators: Creators,
    @SerializedName("description")
    val description: String,
    @SerializedName("endYear")
    val endYear: Int,
    @SerializedName("events")
    val events: Events,
    @SerializedName("id")
    val id: Int,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("next")
    val next: Next,
    @SerializedName("previous")
    val previous: Previous,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("startYear")
    val startYear: Int,
    @SerializedName("stories")
    val stories: Stories,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("urls")
    val urls: List<Any>
)
