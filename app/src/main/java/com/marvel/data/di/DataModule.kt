package com.marvel.data.di

import androidx.room.Room
import com.marvel.data.local.FavoriteDatabase
import com.marvel.data.local.DatabaseMapper
import com.marvel.data.model.FavoriteCharacterDto
import com.marvel.data.remote.CharacterRepositoryImpl
import com.marvel.data.local.FavoriteRepositoryImpl
import com.marvel.data.remote.MarvelApiService
import com.marvel.domain.repository.CharactersRepository
import com.marvel.domain.repository.FavoriteRepository
import com.marvel.presentation.MarvelApplication
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
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MarvelApiService::class.java)
    }

    @Provides
    fun provideCharactersRepository(
        database: FavoriteDatabase,
        service: MarvelApiService
    ): CharactersRepository {
        return CharacterRepositoryImpl(database, service)
    }

    @Provides
    fun providesFavoriteDatabase(
        application: MarvelApplication
    ): FavoriteDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            FavoriteDatabase::class.java,
            FavoriteCharacterDto.TABLE
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
}