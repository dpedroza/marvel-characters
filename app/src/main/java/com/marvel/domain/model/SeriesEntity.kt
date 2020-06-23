package com.marvel.domain.model

import android.os.Parcel
import android.os.Parcelable

data class SeriesEntity(
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

    companion object CREATOR : Parcelable.Creator<SeriesEntity> {
        override fun createFromParcel(parcel: Parcel): SeriesEntity {
            return SeriesEntity(parcel)
        }

        override fun newArray(size: Int): Array<SeriesEntity?> {
            return arrayOfNulls(size)
        }
    }
}
