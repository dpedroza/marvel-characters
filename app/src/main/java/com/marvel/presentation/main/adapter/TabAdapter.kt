package com.marvel.presentation.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabAdapter(manager: FragmentManager) : FragmentPagerAdapter(
    manager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    private val fragments: MutableList<Fragment> = mutableListOf()
    private val titles: MutableList<String> = mutableListOf()

    fun add(frag: Fragment, title: String) {
        this.fragments.add(frag)
        this.titles.add(title)
    }

    override fun getCount(): Int = fragments.count()

    override fun getItem(position: Int) = fragments[position]

    override fun getPageTitle(position: Int) = titles[position]
}
