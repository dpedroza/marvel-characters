package com.marvel.presentation.di

import com.marvel.data.di.DataModule
import com.marvel.domain.di.DomainModule
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        DomainModule::class
    ]
)
interface ApplicationComponent {
    fun presentationComponent(): PresentationComponent
}