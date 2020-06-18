package com.marvel.presentation.mapper

interface Mapper<in I, out T> {

    fun transform(input: I): T
}
