package com.marvel.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterViewObject(
    val id: Int,
    val name: String,
    val bannerURL: String,
    var isFavorite: Boolean,
    val description: String
) : Parcelable {

    fun updateFavorite() {
        this.isFavorite = !isFavorite
    }
}
