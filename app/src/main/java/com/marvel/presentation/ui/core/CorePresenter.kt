package com.marvel.presentation.ui.core

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class CorePresenter<T : BaseView> : BasePresenter<T> {

    protected var view: T? = null
    private var disposables: CompositeDisposable? = null

    override fun attach(view: T) {
        this.view = view
        disposables = CompositeDisposable()
    }

    override fun detach() {
        this.view = null
        disposables?.clear()
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables?.add(disposable)
    }
}