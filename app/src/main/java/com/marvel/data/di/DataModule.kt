package com.marvel.data.di

import androidx.room.Room
import com.marvel.data.local.FavoriteDatabase
import com.marvel.data.mapper.ResponseMapper
import com.marvel.data.model.FavoriteCharacterDto
import com.marvel.data.repository.CharacterRepositoryImpl
import com.marvel.data.repository.FavoriteRepositoryImpl
import com.marvel.data.service.MarvelApiService
import com.marvel.domain.repository.CharactersRepository
import com.marvel.domain.repository.FavoriteRepository
import com.marvel.presentation.MarvelApplication
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule(
    val application: MarvelApplication
) {

    @Provides
    fun provideApplication(): MarvelApplication {
        return application
    }

    @Provides
    fun provideMarvelApiService(): MarvelApiService {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(MarvelApiService::class.java)
    }

    @Provides
    fun provideCharactersRepository(
        database: FavoriteDatabase,
        service: MarvelApiService,
        mapper: ResponseMapper
    ): CharactersRepository {
        return CharacterRepositoryImpl(database, service, mapper)
    }

    @Provides
    fun providesFavoriteDatabase(
        application: MarvelApplication
    ): FavoriteDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            FavoriteDatabase::class.java,
            FavoriteCharacterDto.TABLE
        ).build()
    }

    @Provides
    fun providesFavoriteRepository(
        database: FavoriteDatabase
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(database)
    }

    @Provides
    fun providesResponseMapper(): ResponseMapper {
        return ResponseMapper()
    }
}