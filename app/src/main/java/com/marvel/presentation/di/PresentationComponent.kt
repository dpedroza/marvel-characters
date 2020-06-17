package com.marvel.presentation.di

import com.marvel.presentation.ui.characters.CharacterFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(target: CharacterFragment)
}