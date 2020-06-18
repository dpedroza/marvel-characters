package com.marvel.presentation.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.marvel.presentation.ui.characters.CharacterFragment
import com.marvel.presentation.ui.favorites.FavoritesFragment

class MainViewPagerAdapter(
    private val context: Context,
    manager: FragmentManager
) : FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CharacterFragment()
            1 -> FavoritesFragment()
            else -> throw IllegalArgumentException("unsupported fragment index")
        }
    }

    override fun getPageTitle(position: Int) = when (position) {
        0 -> context.getString(CharacterFragment.TITLE_ID)
        1 -> context.getString(FavoritesFragment.TITLE_ID)
        else -> throw IllegalArgumentException("unsupported fragment index")
    }
}
