package com.marvel.presentation.ui.core

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showMessage(messageId: Int)
}
