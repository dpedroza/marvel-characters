package com.marvel.domain.model


data class GetCharactersParameters(
    val offset: Int
) {
    var query: String? = null
        set(value) {
            field = if (value.isNullOrEmpty()) null else value
        }
}