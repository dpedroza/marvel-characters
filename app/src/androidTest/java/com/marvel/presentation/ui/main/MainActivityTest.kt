package com.marvel.presentation.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.marvel.R
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun assertInitialState() {
        onView(withText("Personagens")).check(matches(isDisplayed()))
        onView(withText("Favoritos")).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_character)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_favorite)).check(matches(not(isDisplayed())))
        onView(withText("Favoritos")).check(matches(isDisplayed()))
    }

    @Test
    fun onLeftSwipeShouldShowFavoritesTab() {
        onView(withId(R.id.viewPager)).perform(swipeLeft())
        onView(withId(R.id.fragment_character)).check(matches(not(isDisplayed())))
        onView(withId(R.id.fragment_favorite)).check(matches(isDisplayed()))
    }
}
