package com.marvel.presentation.di.component

import com.marvel.presentation.di.module.PresentationModule
import com.marvel.presentation.di.scope.UiScope
import com.marvel.presentation.detail.DetailActivity
import com.marvel.presentation.main.characters.CharacterFragment
import com.marvel.presentation.main.favorites.FavoritesFragment
import dagger.Subcomponent

@UiScope
@Subcomponent(modules = [PresentationModule::class])
interface UiComponent {
    fun inject(target: DetailActivity)
    fun inject(target: CharacterFragment)
    fun inject(target: FavoritesFragment)
}
