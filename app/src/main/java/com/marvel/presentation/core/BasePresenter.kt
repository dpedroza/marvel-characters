package com.marvel.presentation.core

interface BasePresenter<in T : BaseView> {
    fun attach(view: T)
    fun detach()
}
