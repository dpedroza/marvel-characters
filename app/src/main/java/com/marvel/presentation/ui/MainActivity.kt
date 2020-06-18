package com.marvel.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.marvel.R
import com.marvel.presentation.ui.characters.CharacterFragment
import com.marvel.presentation.ui.favorites.FavoritesFragment

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        val adapter = TabAdapter(supportFragmentManager)
        adapter.add(CharacterFragment(), getString(R.string.heroes))
        adapter.add(FavoritesFragment(), getString(R.string.favorites))
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
