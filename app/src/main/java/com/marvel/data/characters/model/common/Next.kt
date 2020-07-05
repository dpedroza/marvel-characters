package com.marvel.data.characters.model.common

import com.google.gson.annotations.SerializedName

data class Next(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String
)
