package com.marvel.presentation.model

data class PaginationData(
    var isFinalPage: Boolean = false,
    var offset: Int = 0
) {
    fun reset() {
        isFinalPage = false
        offset = 0
    }
}