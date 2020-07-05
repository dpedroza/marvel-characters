package com.marvel.data.model.api.comics

import com.google.gson.annotations.SerializedName
import com.marvel.data.model.api.common.Data

data class GetComicsApiResponse(
    @SerializedName("attributionHTML")
    val attributionHTML: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    val `data`: Data<ComicsRemoteObject>,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("status")
    val status: String
)
