package com.marvel.data.model.api

import com.google.gson.annotations.SerializedName

data class Collection(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String
)
