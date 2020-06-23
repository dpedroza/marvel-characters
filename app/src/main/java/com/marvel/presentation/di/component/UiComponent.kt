package com.marvel.presentation.di.component

import com.marvel.presentation.di.module.PresentationModule
import com.marvel.presentation.di.scope.PerFragment
import com.marvel.presentation.ui.detail.DetailActivity
import com.marvel.presentation.ui.main.characters.CharacterFragment
import com.marvel.presentation.ui.main.favorites.FavoritesFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [PresentationModule::class])
interface UiComponent {
    fun inject(target: DetailActivity)
    fun inject(target: CharacterFragment)
    fun inject(target: FavoritesFragment)
}