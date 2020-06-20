package com.marvel.presentation.di.module

import com.marvel.domain.usecase.GetCharacters
import com.marvel.domain.usecase.GetFavorites
import com.marvel.domain.usecase.UpdateFavorite
import com.marvel.presentation.di.scope.PerFragment
import com.marvel.presentation.mapper.ViewObjectMapper
import com.marvel.presentation.model.PaginationData
import com.marvel.presentation.ui.main.characters.CharacterPresenter
import com.marvel.presentation.ui.main.favorites.FavoritesPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @PerFragment
    @Provides
    fun providesCharacterPresenter(
        addFavorite: UpdateFavorite,
        getCharacters: GetCharacters,
        mapper: ViewObjectMapper,
        pagination: PaginationData
    ): CharacterPresenter {
        return CharacterPresenter(
            addFavorite,
            getCharacters,
            mapper,
            pagination
        )
    }

    @PerFragment
    @Provides
    fun providesFavoritesPresenter(
        getFavorites: GetFavorites,
        mapper: ViewObjectMapper
    ): FavoritesPresenter {
        return FavoritesPresenter(getFavorites, mapper)
    }

    @PerFragment
    @Provides
    fun providesViewObjectMapper(): ViewObjectMapper {
        return ViewObjectMapper()
    }

    @PerFragment
    @Provides
    fun providesPaginationData(): PaginationData {
        return PaginationData()
    }
}
