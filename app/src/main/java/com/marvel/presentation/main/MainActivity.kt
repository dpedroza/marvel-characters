package com.marvel.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marvel.R
import com.marvel.presentation.main.adapter.TabAdapter
import com.marvel.presentation.main.characters.CharacterFragment
import com.marvel.presentation.main.favorites.FavoritesFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        val adapter = TabAdapter(supportFragmentManager)
        adapter.add(CharacterFragment.newInstance(), getString(R.string.characters))
        adapter.add(FavoritesFragment.newInstance(), getString(R.string.favorites))
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
