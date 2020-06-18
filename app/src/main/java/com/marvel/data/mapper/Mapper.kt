package com.marvel.data.mapper

interface Mapper<in R, out T> {

    fun transform(input: R): T
}
