package com.arudo.catatube.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.arudo.catatube.R
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var mainActivityRule = activityScenarioRule<MainActivity>()

    @Test
    fun mainActivityMovie() {
        onView(withText(R.string.movie)).perform(click())
        onView(allOf(withId(R.id.rvMovie), isDisplayed())).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20)
        )
    }

    @Test
    fun mainActivityMovieDetail() {
        onView(withText(R.string.movie)).perform(click())
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.imgShow)).perform(scrollTo()).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.imgBackground)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtTitle)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtSubTitle)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtRating)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtQuote)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtOverview)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtStatus)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtBudget)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtRevenue)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun mainActivityMovieFavorite() {
        onView(withText(R.string.movie)).perform(click())
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favoriteBtn)).perform(scrollTo()).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.favoriteBtn)).perform(scrollTo()).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        onView(withId(R.id.favoriteBtn)).perform(scrollTo()).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.favorite)).perform(click())
        onView(withText(R.string.movie)).perform(click())
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.oldest)).perform(click())
        onView(withText(R.string.movie)).perform(click())
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.newest)).perform(click())
        onView(withText(R.string.movie)).perform(click())
        onView(withId(R.id.rvFavoriteMovie)).check(matches(isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                GeneralSwipeAction(
                    Swipe.FAST,
                    GeneralLocation.CENTER,
                    GeneralLocation.CENTER_RIGHT,
                    Press.FINGER
                )
            )
        )
    }

    @Test
    fun mainActivityTelevision() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(allOf(withId(R.id.rvTelevision), isDisplayed())).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20)
        )
    }

    @Test
    fun mainActivityTelevisionDetail() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rvTelevision)).check(matches(isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.imgShow)).perform(scrollTo()).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.imgBackground)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtTitle)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtSubTitle)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtRating)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtOverview)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtStatus)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtType)).perform(scrollTo())
            .check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun mainActivityTelevisionFavorite() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rvTelevision)).check(matches(isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favoriteBtn)).perform(scrollTo()).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.rvTelevision)).check(matches(isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.favoriteBtn)).perform(scrollTo()).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.rvTelevision)).check(matches(isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        onView(withId(R.id.favoriteBtn)).perform(scrollTo()).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.favorite)).perform(click())
        onView(withText(R.string.tv_show)).perform(click())
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.oldest)).perform(click())
        onView(withText(R.string.tv_show)).perform(click())
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.newest)).perform(click())
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rvFavoriteTelevision)).check(matches(isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                GeneralSwipeAction(
                    Swipe.FAST,
                    GeneralLocation.CENTER,
                    GeneralLocation.CENTER_RIGHT,
                    Press.FINGER
                )
            )
        )
    }
}