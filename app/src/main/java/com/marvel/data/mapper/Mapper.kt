package com.marvel.data.mapper

interface Mapper<in R, L, out T> {

    fun transform(remoteInput: R, localInput: L): T
}
