package com.marvel.presentation.di

import com.marvel.presentation.ui.characters.CharacterFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [PresentationModule::class])
interface UiComponent {
    fun inject(target: CharacterFragment)
}