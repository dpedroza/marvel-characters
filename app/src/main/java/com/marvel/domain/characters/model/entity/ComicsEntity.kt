package com.marvel.domain.characters.model.entity

import android.os.Parcel
import android.os.Parcelable

data class ComicsEntity(
    val imageUrl: String,
    val name: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imageUrl)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ComicsEntity> {
        override fun createFromParcel(parcel: Parcel): ComicsEntity {
            return ComicsEntity(parcel)
        }

        override fun newArray(size: Int): Array<ComicsEntity?> {
            return arrayOfNulls(size)
        }
    }
}
