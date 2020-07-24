package com.marvel.data.di.module

import android.content.Context
import androidx.room.Room
import com.marvel.BuildConfig
import com.marvel.data.characters.repository.CharacterRepositoryImpl
import com.marvel.data.characters.service.MarvelApiService
import com.marvel.data.favorites.database.FavoriteDao
import com.marvel.data.favorites.database.FavoriteDatabase
import com.marvel.data.favorites.model.CharacterDto
import com.marvel.data.favorites.repository.FavoriteRepositoryImpl
import com.marvel.domain.characters.CharactersRepository
import com.marvel.domain.favorites.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

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
        @ApplicationContext context: Context
    ): FavoriteDatabase {
        return Room.databaseBuilder(
            context,
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
