package com.marvel

import com.marvel.data.di.DataModule
import com.marvel.domain.di.DomainModule
import com.marvel.presentation.di.UiComponent
import com.marvel.presentation.ui.characters.CharacterFragment
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        DomainModule::class
    ]
)
interface ApplicationComponent {
    fun uiComponent(): UiComponent
}