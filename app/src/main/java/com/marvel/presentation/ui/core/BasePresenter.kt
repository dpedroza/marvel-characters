package com.marvel.presentation.ui.core

interface BasePresenter<in T : BaseView> {
    fun attach(view: T)
    fun detach()
}