package com.marvel.presentation.di.module

import com.marvel.domain.usecase.GetCharacters
import com.marvel.domain.usecase.GetFavorites
import com.marvel.domain.usecase.UpdateFavorite
import com.marvel.presentation.di.scope.PerFragment
import com.marvel.presentation.mapper.ViewObjectMapper
import com.marvel.presentation.mapper.ViewObjectMapperImpl
import com.marvel.presentation.ui.detail.DetailContract
import com.marvel.presentation.ui.detail.DetailPresenter
import com.marvel.presentation.ui.main.characters.CharacterPresenter
import com.marvel.presentation.ui.main.characters.CharactersContract
import com.marvel.presentation.ui.main.favorites.FavoritesContract
import com.marvel.presentation.ui.main.favorites.FavoritesPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @PerFragment
    @Provides
    fun providesCharacterPresenter(
        updateFavorite: UpdateFavorite,
        getCharacters: GetCharacters,
        mapper: ViewObjectMapper
    ): CharactersContract.Presenter {
        return CharacterPresenter(
            updateFavorite,
            getCharacters,
            mapper
        )
    }

    @PerFragment
    @Provides
    fun providesDetailPresenter(
        updateFavorite: UpdateFavorite,
        mapper: ViewObjectMapper
    ): DetailContract.Presenter {
        return DetailPresenter(updateFavorite, mapper)
    }

    @PerFragment
    @Provides
    fun providesFavoritesPresenter(
        getFavorites: GetFavorites,
        mapper: ViewObjectMapper
    ): FavoritesContract.Presenter {
        return FavoritesPresenter(getFavorites, mapper)
    }

    @PerFragment
    @Provides
    fun providesViewObjectMapper(): ViewObjectMapper {
        return ViewObjectMapperImpl()
    }
}
