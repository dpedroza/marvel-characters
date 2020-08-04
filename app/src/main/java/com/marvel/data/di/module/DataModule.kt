package com.marvel.data.di.module

import android.content.Context
import androidx.room.Room
import com.marvel.BuildConfig
import com.marvel.data.characters.interceptor.InterceptorFactory
import com.marvel.data.characters.repository.CharacterRepositoryImpl
import com.marvel.data.characters.service.ApiService
import com.marvel.data.favorites.database.FavoriteDao
import com.marvel.data.favorites.database.FavoriteDatabase
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
    fun provideApiService(
        client: OkHttpClient,
        converter: GsonConverterFactory,
        adapter: RxJava2CallAdapterFactory
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MARVEL_BASE_URL)
            .addConverterFactory(converter)
            .addCallAdapterFactory(adapter)
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun providesDatabase(@ApplicationContext context: Context): FavoriteDatabase {
        val databaseClass = FavoriteDatabase::class.java
        val databaseName = FavoriteDatabase.NAME
        return Room.databaseBuilder(context, databaseClass, databaseName)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun providesInterceptor(): Interceptor {
        return InterceptorFactory.create()
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
    fun provideCharactersRepository(dao: FavoriteDao, service: ApiService): CharactersRepository {
        return CharacterRepositoryImpl(dao, service)
    }

    @Provides
    fun provideFavoriteDataAccessObject(database: FavoriteDatabase): FavoriteDao {
        return database.favoriteDao()
    }

    @Provides
    fun providesFavoriteRepository(dao: FavoriteDao): FavoritesRepository {
        return FavoritesRepositoryImpl(dao)
    }
}
