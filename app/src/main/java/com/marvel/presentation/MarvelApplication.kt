package com.marvel.presentation

import android.app.Application
import com.marvel.presentation.di.ApplicationComponent
import com.marvel.presentation.di.DaggerApplicationComponent

class MarvelApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        // Reference to the application graph that is used across the whole app
        component = DaggerApplicationComponent.create()
    }
}
