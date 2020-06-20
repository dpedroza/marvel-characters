package com.marvel.data.favorites.repo

import com.marvel.data.database.FavoriteDao
import com.marvel.data.favorites.mapper.DatabaseMapper
import com.marvel.domain.repository.FavoriteRepository
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteRepositoryTest {

    @Mock
    lateinit var dao: FavoriteDao

    @Spy
    lateinit var mapper: DatabaseMapper

    private lateinit var repository: FavoriteRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

}