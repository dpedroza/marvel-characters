package com.marvel.data.characters.model.characters

import com.google.gson.annotations.SerializedName
import com.marvel.data.characters.model.common.Data

data class GetCharactersApiResponse(
    @SerializedName("attributionHTML")
    val attributionHTML: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    val `data`: Data<CharacterRemoteObject>,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("status")
    val status: String
)
