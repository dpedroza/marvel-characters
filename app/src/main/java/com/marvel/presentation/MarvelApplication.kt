package com.marvel.presentation

import android.app.Application
import com.marvel.data.di.module.DataModule
import com.marvel.presentation.di.component.ApplicationComponent
import com.marvel.presentation.di.component.DaggerApplicationComponent

class MarvelApplication : Application() {

    companion object {
        lateinit var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }

    private fun setupDagger() {
        component = DaggerApplicationComponent.builder()
            .dataModule(DataModule(this))
            .build()
    }
}
