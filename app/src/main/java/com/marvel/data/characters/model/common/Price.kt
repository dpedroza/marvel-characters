package com.marvel.data.characters.model.common

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("price")
    val price: Int,
    @SerializedName("type")
    val type: String
)
