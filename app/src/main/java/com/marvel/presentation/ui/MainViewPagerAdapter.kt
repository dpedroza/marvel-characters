package com.marvel.presentation.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.marvel.R
import com.marvel.presentation.ui.characters.CharacterFragment
import com.marvel.presentation.ui.favorites.FavoritesFragment

class MainViewPagerAdapter(manager: FragmentManager, val context: Context) :
    FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CharacterFragment()
            1 -> FavoritesFragment()
            else -> throw IllegalArgumentException("unsupported fragment index")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.getString(R.string.heroes)
            1 -> context.getString(R.string.favorites)
            else -> throw IllegalArgumentException("unsupported fragment index")
        }
    }
}
