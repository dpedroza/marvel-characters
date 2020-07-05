package com.marvel.presentation.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
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
        onView(withText("Characters")).check(matches(isDisplayed()))
        onView(withText("Favorites")).check(matches(isDisplayed()))

        onView(withId(R.id.fragment_character)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_favorite)).check(matches(not(isDisplayed())))
    }

    @Test
    fun assertViewPagerBehavior() {
        onView(withId(R.id.viewPager)).perform(swipeLeft())

        onView(withId(R.id.fragment_character)).check(matches(not(isDisplayed())))
        onView(withId(R.id.fragment_favorite)).check(matches(isDisplayed()))

        onView(withId(R.id.viewPager)).perform(swipeRight())

        onView(withId(R.id.fragment_character)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_favorite)).check(matches(not(isDisplayed())))
    }

    @Test
    fun assertTabLayoutBehavior() {

        onView(withText("Characters")).perform(click())

        onView(withId(R.id.fragment_character)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_favorite)).check(matches(not(isDisplayed())))

        onView(withText("Favorites")).perform(click())

        onView(withId(R.id.fragment_character)).check(matches(not(isDisplayed())))
        onView(withId(R.id.fragment_favorite)).check(matches(isDisplayed()))
    }
}
