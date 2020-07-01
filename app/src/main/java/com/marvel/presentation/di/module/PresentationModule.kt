package com.marvel.presentation.di.module

import com.marvel.domain.usecase.GetCharacters
import com.marvel.domain.usecase.GetFavorites
import com.marvel.domain.usecase.UpdateFavorite
import com.marvel.presentation.di.scope.UiScope
import com.marvel.presentation.mapper.ViewObjectMapper
import com.marvel.presentation.ui.detail.DetailContract
import com.marvel.presentation.ui.detail.DetailPresenter
import com.marvel.presentation.ui.main.characters.CharacterPresenter
import com.marvel.presentation.ui.main.characters.CharactersContract
import com.marvel.presentation.ui.main.favorites.FavoritesContract
import com.marvel.presentation.ui.main.favorites.FavoritesPresenter
import com.marvel.presentation.ui.main.rx.SchedulerProvider
import com.marvel.presentation.ui.main.rx.SchedulerProviderImpl
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @UiScope
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

    @UiScope
    @Provides
    fun providesDetailPresenter(
        updateFavorite: UpdateFavorite,
        mapper: ViewObjectMapper
    ): DetailContract.Presenter {
        return DetailPresenter(updateFavorite, mapper)
    }

    @UiScope
    @Provides
    fun providesFavoritesPresenter(
        getFavorites: GetFavorites,
        schedulerProvider: SchedulerProvider
    ): FavoritesContract.Presenter {
        return FavoritesPresenter(getFavorites, schedulerProvider)
    }

    @UiScope
    @Provides
    fun providesViewObjectMapper(): ViewObjectMapper {
        return ViewObjectMapper()
    }

    @UiScope
    @Provides
    fun providesScheduleProvider(): SchedulerProvider {
        return SchedulerProviderImpl()
    }
}
