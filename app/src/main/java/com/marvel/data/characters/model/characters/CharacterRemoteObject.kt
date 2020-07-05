package com.marvel.data.characters.model.characters

import com.google.gson.annotations.SerializedName
import com.marvel.data.characters.model.common.Stories
import com.marvel.data.characters.model.common.Comics
import com.marvel.data.characters.model.common.Events
import com.marvel.data.characters.model.common.Series
import com.marvel.data.characters.model.common.Thumbnail
import com.marvel.data.characters.model.common.Url

data class CharacterRemoteObject(
    @SerializedName("comics")
    val comics: Comics,
    @SerializedName("description")
    val description: String,
    @SerializedName("events")
    val events: Events,
    @SerializedName("id")
    val id: Int,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("series")
    val series: Series,
    @SerializedName("stories")
    val stories: Stories,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("urls")
    val urls: List<Url>
)
