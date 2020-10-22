package com.arudo.catatube.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.arudo.catatube.R
import com.arudo.catatube.utils.DataDummy
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val dataDummyMovie = DataDummy.movieDummyData()
    private val dataDummyTelevision = DataDummy.televisionDummyData()


    @get:Rule
    var mainActivityRule = activityScenarioRule<MainActivity>()

    @Test
    fun mainActivityMovie() {
        onView(withText("Movie")).perform(click())
        onView(allOf(withId(R.id.rvMovieTV), isDisplayed())).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        dataDummyMovie.size
                )
        )
    }

    @Test
    fun mainActivityMovieDetail() {
        onView(withText("Movie")).perform(click())
        onView(allOf(withId(R.id.rvMovieTV), isDisplayed())).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        click()
                )
        )
        onView(withId(R.id.imgShow)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withTagValue(CoreMatchers.equalTo(dataDummyMovie[0].image))))
        onView(withId(R.id.imgBackground)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withTagValue(CoreMatchers.equalTo(dataDummyMovie[0].image))))
        onView(withId(R.id.txtTitle)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText(dataDummyMovie[0].title)))
        onView(withId(R.id.txtSubTitle)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText("${dataDummyMovie[0].year} | ${dataDummyMovie[0].genre} | ${dataDummyMovie[0].duration}")))
        onView(withId(R.id.txtRating)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText("Rating: ${dataDummyMovie[0].rating}/100%")))
        onView(withId(R.id.txtQuote)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText(dataDummyMovie[0].quote)))
        onView(withId(R.id.txtOverview)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText(dataDummyMovie[0].overview)))
        onView(withId(R.id.txtStatus)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText(dataDummyMovie[0].status)))
        onView(withId(R.id.txtLanguage)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText(dataDummyMovie[0].language)))
        onView(withId(R.id.txtBudget)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText("$ ${dataDummyMovie[0].budget}")))
        onView(withId(R.id.txtRevenue)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText("$ ${dataDummyMovie[0].revenue}")))
    }


    @Test
    fun mainActivityTelevision() {
        onView(withText("TV Show")).perform(click())
        onView(allOf(withId(R.id.rvMovieTV), isDisplayed())).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        dataDummyTelevision.size
                )
        )
    }

    @Test
    fun mainActivityTelevisionDetail() {
        onView(withText("TV Show")).perform(click())
        onView(allOf(withId(R.id.rvMovieTV), isDisplayed())).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        click()
                )
        )
        onView(withId(R.id.imgShow)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withTagValue(CoreMatchers.equalTo(dataDummyTelevision[0].image))))
        onView(withId(R.id.imgBackground)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withTagValue(CoreMatchers.equalTo(dataDummyTelevision[0].image))))
        onView(withId(R.id.txtTitle)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText(dataDummyTelevision[0].title)))
        onView(withId(R.id.txtSubTitle)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText("${dataDummyTelevision[0].year} | ${dataDummyTelevision[0].genre} | ${dataDummyTelevision[0].duration}")))
        onView(withId(R.id.txtRating)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText("Rating: ${dataDummyTelevision[0].rating}/100%")))
        onView(withId(R.id.txtQuote)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText(dataDummyTelevision[0].quote)))
        onView(withId(R.id.txtOverview)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText(dataDummyTelevision[0].overview)))
        onView(withId(R.id.txtStatus)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText(dataDummyTelevision[0].status)))
        onView(withId(R.id.txtLanguage)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText(dataDummyTelevision[0].language)))
        onView(withId(R.id.txtBudget)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText("$ ${dataDummyTelevision[0].budget}")))
        onView(withId(R.id.txtRevenue)).perform(scrollTo()).check(matches(isCompletelyDisplayed())).check(matches(withText("$ ${dataDummyTelevision[0].revenue}")))
    }
}