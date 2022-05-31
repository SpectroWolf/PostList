package com.example.postlist.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.postlist.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Test
    fun checkIfMainActivityIsVisible() {
        Espresso.onView(withId(R.id.linearLayoutMain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkIfMainActivityTitleIsDisplayed() {
        Espresso.onView(withId(R.id.tv_main_app_name))
            .check(ViewAssertions.matches(withText(R.string.postagens_recentes)))
    }

}