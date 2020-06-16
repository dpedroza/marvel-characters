package com.marvel.data.model


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("type")
    val type: String
)