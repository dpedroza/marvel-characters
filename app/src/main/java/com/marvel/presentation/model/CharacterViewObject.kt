package com.marvel.presentation.model

import android.os.Parcel
import android.os.Parcelable

data class CharacterViewObject(
    val id: Int,
    val name: String,
    val bannerURL: String,
    var isFavorite: Boolean,
    val description: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(bannerURL)
        parcel.writeByte(if (isFavorite) 1 else 0)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterViewObject> {
        override fun createFromParcel(parcel: Parcel): CharacterViewObject {
            return CharacterViewObject(parcel)
        }

        override fun newArray(size: Int): Array<CharacterViewObject?> {
            return arrayOfNulls(size)
        }
    }
}
