package com.marvel.data.model.api

import com.google.gson.annotations.SerializedName

data class GetSeriesApiResponse(
    @SerializedName("attributionHTML")
    val attributionHTML: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    val `data`: Data<SeriesRemoteObject>,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("status")
    val status: String
)
