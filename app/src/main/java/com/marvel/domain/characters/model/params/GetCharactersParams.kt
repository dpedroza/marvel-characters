package com.marvel.domain.characters.model.params

data class GetCharactersParams(
    var offset: Int = 0,
    var _query: String? = null
) {
    val query: String?
        get() = if (_query.isNullOrEmpty()) null else _query
}
