package com.marvel.domain.di.module

import com.marvel.domain.characters.CharactersRepository
import com.marvel.domain.characters.model.result.GetCharactersResult
import com.marvel.domain.characters.model.result.GetComicsResult
import com.marvel.domain.characters.model.result.GetSeriesResult
import com.marvel.domain.characters.model.params.GetCharactersParams
import com.marvel.domain.characters.model.params.GetComicsParams
import com.marvel.domain.characters.model.params.GetSeriesParams
import com.marvel.domain.characters.usecase.GetCharacters
import com.marvel.domain.characters.usecase.GetComics
import com.marvel.domain.characters.usecase.GetSeries
import com.marvel.domain.characters.model.entity.CharacterEntity
import com.marvel.domain.UseCase
import com.marvel.domain.favorites.FavoritesRepository
import com.marvel.domain.favorites.usecase.GetFavorites
import com.marvel.domain.favorites.usecase.UpdateFavorites
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
        favoritesRepository: FavoritesRepository
    ): UseCase.FromSingle.WithoutInput<List<CharacterEntity>> {
        return GetFavorites(
            favoritesRepository
        )
    }

    @Provides
    fun provideUpdateFavoriteUseCase(
        favoritesRepository: FavoritesRepository
    ): UseCase.FromCompletable.WithInput<CharacterEntity> {
        return UpdateFavorites(
            favoritesRepository
        )
    }
}
