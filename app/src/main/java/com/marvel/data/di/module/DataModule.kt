package com.marvel.data.di.module

import android.content.Context
import androidx.room.Room
import com.marvel.BuildConfig
import com.marvel.data.characters.interceptor.InterceptorProvider
import com.marvel.data.characters.repository.CharacterRepositoryImpl
import com.marvel.data.characters.service.MarvelApiService
import com.marvel.data.favorites.database.FavoriteDao
import com.marvel.data.favorites.database.FavoriteDatabase
import com.marvel.data.favorites.model.CharacterDto
import com.marvel.data.favorites.repository.FavoritesRepositoryImpl
import com.marvel.domain.characters.CharactersRepository
import com.marvel.domain.favorites.FavoritesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    fun provideMarvelApiService(
        client: OkHttpClient,
        converter: GsonConverterFactory,
        adapter: RxJava2CallAdapterFactory
    ): MarvelApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MARVEL_BASE_URL)
            .addConverterFactory(converter)
            .addCallAdapterFactory(adapter)
            .client(client)
            .build()
            .create(MarvelApiService::class.java)
    }

    @Provides
    fun provideOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun providesInterceptor(): Interceptor {
        return InterceptorProvider.provides()
    }

    @Provides
    fun providesGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun providesCallAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
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
    ): FavoritesRepository {
        return FavoritesRepositoryImpl(
            dao
        )
    }
}
