package com.marvel.presentation.di.module

import com.marvel.domain.usecase.GetCharacters
import com.marvel.domain.usecase.GetFavorites
import com.marvel.domain.usecase.UpdateFavorite
import com.marvel.presentation.di.scope.PerFragment
import com.marvel.presentation.mapper.ViewObjectMapper
import com.marvel.presentation.ui.main.characters.CharacterPresenter
import com.marvel.presentation.ui.main.characters.CharactersContract
import com.marvel.presentation.ui.main.rx.SchedulerProvider
import com.marvel.presentation.ui.main.favorites.FavoritesContract
import com.marvel.presentation.ui.main.favorites.FavoritesPresenter
import com.marvel.presentation.ui.main.rx.SchedulerProviderImpl
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @PerFragment
    @Provides
    fun providesCharacterPresenter(
        addFavorite: UpdateFavorite,
        getCharacters: GetCharacters,
        schedulerProvider: SchedulerProvider
    ): CharactersContract.Presenter {
        return CharacterPresenter(
            addFavorite,
            getCharacters,
            schedulerProvider
        )
    }

    @PerFragment
    @Provides
    fun providesFavoritesPresenter(
        getFavorites: GetFavorites,
        schedulerProvider: SchedulerProvider
    ): FavoritesContract.Presenter {
        return FavoritesPresenter(getFavorites, schedulerProvider)
    }

    @PerFragment
    @Provides
    fun providesViewObjectMapper(): ViewObjectMapper {
        return ViewObjectMapper()
    }

    @PerFragment
    @Provides
    fun providesScheduleProvider(): SchedulerProvider {
        return SchedulerProviderImpl()
    }
}
