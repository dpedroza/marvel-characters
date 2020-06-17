package com.marvel.data.di

import com.marvel.data.CharacterRepositoryImpl
import com.marvel.data.MarvelApiService
import com.marvel.domain.CharactersRepository
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// @Module informs Dagger that this class is a Dagger Module
@Module
class DataModule {

    // @Provides tell Dagger how to create instances of the type that this function
    // returns (i.e. MarvelApiService).
    // Function parameters are the dependencies of this type.
    @Provides
    fun provideMarvelApiService(): MarvelApiService {
        // Whenever Dagger needs to provide an instance of type MarvelApiService,
        // this code (the one inside the @Provides method) is run.
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(MarvelApiService::class.java)
    }

    @Provides
    fun provideCharactersRepository(characterService: MarvelApiService): CharactersRepository {
        return CharacterRepositoryImpl(characterService)
    }
}