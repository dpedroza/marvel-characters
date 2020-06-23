package com.marvel.data.di

import androidx.room.Room
import com.marvel.BuildConfig
import com.marvel.data.favorites.repo.FavoriteRepositoryImpl
import com.marvel.data.database.FavoriteDao
import com.marvel.data.database.FavoriteDatabase
import com.marvel.data.favorites.mapper.DatabaseMapper
import com.marvel.data.model.CharacterDto
import com.marvel.data.characters.repo.CharacterRepositoryImpl
import com.marvel.data.characters.mapper.ResponseMapper
import com.marvel.data.characters.service.MarvelApiService
import com.marvel.domain.repository.CharactersRepository
import com.marvel.domain.repository.FavoriteRepository
import com.marvel.presentation.application.MarvelApplication
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule(private val application: MarvelApplication) {

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
        dao: FavoriteDao,
        service: MarvelApiService
    ): CharactersRepository {
        return CharacterRepositoryImpl(
            dao,
            service
        )
    }

    @Provides
    fun provideFavoriteDataAccessObject(
        database: FavoriteDatabase
    ): FavoriteDao {
        return database.favoriteDao()
    }

    @Provides
    fun providesFavoriteDatabase(
        application: MarvelApplication
    ): FavoriteDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            FavoriteDatabase::class.java,
            CharacterDto.TABLE
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun providesFavoriteRepository(
        dao: FavoriteDao
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(
            dao
        )
    }
}