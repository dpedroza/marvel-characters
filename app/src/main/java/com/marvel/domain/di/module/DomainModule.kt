package com.marvel.domain.di.module

import com.marvel.domain.characters.CharactersRepository
import com.marvel.domain.characters.model.GetCharactersResult
import com.marvel.domain.characters.model.GetComicsResult
import com.marvel.domain.characters.model.GetSeriesResult
import com.marvel.domain.characters.params.GetCharactersParams
import com.marvel.domain.characters.params.GetComicsParams
import com.marvel.domain.characters.params.GetSeriesParams
import com.marvel.domain.characters.usecase.GetCharacters
import com.marvel.domain.characters.usecase.GetComics
import com.marvel.domain.characters.usecase.GetSeries
import com.marvel.domain.core.CharacterEntity
import com.marvel.domain.core.UseCase
import com.marvel.domain.favorites.FavoriteRepository
import com.marvel.domain.favorites.usecase.GetFavorites
import com.marvel.domain.favorites.usecase.UpdateFavorite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class DomainModule {

    @Provides
    fun provideGetCharactersUseCase(
        characterRepository: CharactersRepository
    ): UseCase.FromSingle.WithInput<GetCharactersParams, GetCharactersResult> {
        return GetCharacters(
            characterRepository
        )
    }

    @Provides
    fun provideGetComicsUseCase(
        characterRepository: CharactersRepository
    ): UseCase.FromSingle.WithInput<GetComicsParams, GetComicsResult> {
        return GetComics(
            characterRepository
        )
    }

    @Provides
    fun provideGetSeriesUseCase(
        characterRepository: CharactersRepository
    ): UseCase.FromSingle.WithInput<GetSeriesParams, GetSeriesResult> {
        return GetSeries(
            characterRepository
        )
    }

    @Provides
    fun provideGetFavoritesUseCase(
        favoriteRepository: FavoriteRepository
    ): UseCase.FromSingle.WithoutInput<List<CharacterEntity>> {
        return GetFavorites(
            favoriteRepository
        )
    }

    @Provides
    fun provideUpdateFavoriteUseCase(
        favoriteRepository: FavoriteRepository
    ): UseCase.FromCompletable.WithInput<CharacterEntity> {
        return UpdateFavorite(
            favoriteRepository
        )
    }
}
