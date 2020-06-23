package com.marvel.domain.model

import android.os.Parcel
import android.os.Parcelable

data class ComicEntity(
    val url: String,
    val name: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ComicEntity> {
        override fun createFromParcel(parcel: Parcel): ComicEntity {
            return ComicEntity(parcel)
        }

        override fun newArray(size: Int): Array<ComicEntity?> {
            return arrayOfNulls(size)
        }
    }
}