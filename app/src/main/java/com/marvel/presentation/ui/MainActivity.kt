package com.marvel.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marvel.R
import com.marvel.presentation.ui.characters.CharacterFragment
import com.marvel.presentation.ui.favorites.FavoritesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            val adapter = TabAdapter(supportFragmentManager)
            adapter.add(CharacterFragment.newInstance(), getString(R.string.heroes))
            adapter.add(FavoritesFragment(), getString(R.string.favorites))
            viewPager.adapter = adapter
            tabLayout.setupWithViewPager(viewPager)
        }
}
