package com.marvel.data.di

import androidx.room.Room
import com.marvel.BuildConfig
import com.marvel.data.local.FavoriteRepositoryImpl
import com.marvel.data.local.database.FavoriteDataAccessObject
import com.marvel.data.local.database.FavoriteDatabase
import com.marvel.data.local.mapper.DatabaseMapper
import com.marvel.data.model.FavoriteCharacterDataTransformationObject
import com.marvel.data.remote.CharacterRepositoryImpl
import com.marvel.data.remote.mapper.ResponseMapper
import com.marvel.data.remote.service.MarvelApiService
import com.marvel.domain.repository.CharactersRepository
import com.marvel.domain.repository.FavoriteRepository
import com.marvel.presentation.application.MarvelApplication
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule(
    private val application: MarvelApplication
) {

    @Provides
    fun provideApplication(): MarvelApplication {
        return application
    }

    @Provides
    fun provideMarvelApiService(): MarvelApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MARVEL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MarvelApiService::class.java)
    }

    @Provides
    fun provideCharactersRepository(
        dao: FavoriteDataAccessObject,
        service: MarvelApiService,
        responseMapper: ResponseMapper
    ): CharactersRepository {
        return CharacterRepositoryImpl(dao, service, responseMapper)
    }

    @Provides
    fun provideFavoriteDataAccessObject(
        database: FavoriteDatabase
    ): FavoriteDataAccessObject {
        return database.favoriteDao()
    }

    @Provides
    fun providesFavoriteDatabase(
        application: MarvelApplication
    ): FavoriteDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            FavoriteDatabase::class.java,
            FavoriteCharacterDataTransformationObject.TABLE
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun providesFavoriteRepository(
        database: FavoriteDatabase,
        mapper: DatabaseMapper
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(database, mapper)
    }

    @Provides
    fun providesDatabaseMapper(): DatabaseMapper {
        return DatabaseMapper()
    }

    @Provides
    fun providesResponseMapper(): ResponseMapper {
        return ResponseMapper()
    }
}