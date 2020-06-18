package com.marvel.presentation.model

data class PaginationData(
    var isFinalPage: Boolean = false,
    var offset: Int = 0
) {
    fun clear() {
        isFinalPage = false
        offset = 0
    }
}