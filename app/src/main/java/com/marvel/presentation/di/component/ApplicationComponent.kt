package com.marvel.presentation.di.component

import com.marvel.data.di.module.DataModule
import com.marvel.domain.di.module.DomainModule
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        DomainModule::class
    ]
)
interface ApplicationComponent {
    fun presentationComponent(): UiComponent
}
