package com.marvel

import android.app.Application

class MarvelApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        // Reference to the application graph that is used across the whole app
        applicationComponent = DaggerApplicationComponent.create()
    }
}
