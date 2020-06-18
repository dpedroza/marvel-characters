package com.marvel.presentation.mapper

interface Mapper<in R, out T> {

    fun transform(input: R): T
}
