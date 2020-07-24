package com.marvel.presentation.di.module

import com.marvel.domain.characters.usecase.GetCharacters
import com.marvel.domain.characters.usecase.GetComics
import com.marvel.domain.characters.usecase.GetSeries
import com.marvel.domain.favorites.usecase.GetFavorites
import com.marvel.domain.favorites.usecase.UpdateFavorite
import com.marvel.presentation.detail.DetailContract
import com.marvel.presentation.detail.DetailPresenter
import com.marvel.presentation.main.characters.CharacterPresenter
import com.marvel.presentation.main.characters.CharactersContract
import com.marvel.presentation.main.favorites.FavoritesContract
import com.marvel.presentation.main.favorites.FavoritesPresenter
import com.marvel.presentation.schedulers.SchedulerProvider
import com.marvel.presentation.schedulers.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class PresentationModule {

    @Provides
    fun providesCharacterPresenter(
        updateFavorite: UpdateFavorite,
        getCharacters: GetCharacters,
        schedulerProvider: SchedulerProvider
    ): CharactersContract.Presenter {
        return CharacterPresenter(
            updateFavorite,
            getCharacters,
            schedulerProvider
        )
    }

    @Provides
    fun providesDetailPresenter(
        updateFavorite: UpdateFavorite,
        getComics: GetComics,
        getSeries: GetSeries,
        schedulerProvider: SchedulerProvider
    ): DetailContract.Presenter {
        return DetailPresenter(
            updateFavorite,
            getComics,
            getSeries,
            schedulerProvider
        )
    }

    @Provides
    fun providesFavoritesPresenter(
        getFavorites: GetFavorites,
        schedulerProvider: SchedulerProvider
    ): FavoritesContract.Presenter {
        return FavoritesPresenter(getFavorites, schedulerProvider)
    }

    @Provides
    fun providesScheduleProvider(): SchedulerProvider {
        return SchedulerProviderImpl()
    }
}
