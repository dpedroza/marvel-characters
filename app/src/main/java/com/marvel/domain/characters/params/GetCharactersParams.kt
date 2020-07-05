package com.marvel.domain.characters.params

class GetCharactersParams {
    var offset: Int = 0
    var query: String? = null
        set(value) {
            field = if (value.isNullOrEmpty()) null else value
        }
}
