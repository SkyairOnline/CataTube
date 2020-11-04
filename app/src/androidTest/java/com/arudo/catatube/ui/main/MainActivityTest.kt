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
import com.arudo.catatube.data.source.CataTubeRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private var cataTubeRepository = CataTubeRepository()

    private val dataDummyMovieList = cataTubeRepository.getMoviesList().value
    private val dataDummyMovieId = dataDummyMovieList?.get(0)?.id
    private val dataDummyMovie = dataDummyMovieId?.let { cataTubeRepository.getMovieData(it).value }

    private val dataDummyTelevisionList = cataTubeRepository.getTelevisionsList().value
    private val dataDummyTelevisionId = dataDummyTelevisionList?.get(0)?.id
    private val dataDummyTelevision =
        dataDummyTelevisionId?.let { cataTubeRepository.getTelevisionData(it).value }

    @get:Rule
    var mainActivityRule = activityScenarioRule<MainActivity>()

    @Test
    fun mainActivityMovie() {
        onView(withText("Movie")).perform(click())
        if (dataDummyMovieList != null) {
            onView(allOf(withId(R.id.rvMovie), isDisplayed())).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    dataDummyMovieList.size
                )
            )
        }
    }

    @Test
    fun mainActivityMovieDetail() {
        onView(withText("Movie")).perform(click())
        onView(allOf(withId(R.id.rvMovie), isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        if (dataDummyMovie != null) {
            onView(withId(R.id.imgShow)).perform(scrollTo()).check(matches(isCompletelyDisplayed()))
                .check(matches(withTagValue(CoreMatchers.equalTo(dataDummyMovie.posterPath))))
            onView(withId(R.id.imgBackground)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withTagValue(CoreMatchers.equalTo(dataDummyMovie.posterPath))))
            onView(withId(R.id.txtTitle)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(dataDummyMovie.title)))
            onView(withId(R.id.txtSubTitle)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(
                    matches(
                        withText(
                            "${dataDummyMovie.releaseDate} | ${
                                dataDummyMovie.runtime?.div(
                                    60
                                )
                            }h ${dataDummyMovie.runtime?.rem(60)}m"
                        )
                    )
                )
            onView(withId(R.id.txtRating)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText("Rating: ${dataDummyMovie.voteAverage?.times(10)}/100%")))
            onView(withId(R.id.txtQuote)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(dataDummyMovie.tagline)))
            onView(withId(R.id.txtOverview)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(dataDummyMovie.overview)))
            onView(withId(R.id.txtStatus)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(dataDummyMovie.status)))
            onView(withId(R.id.txtBudget)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText("$ ${dataDummyMovie.budget}")))
            onView(withId(R.id.txtRevenue)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText("$ ${dataDummyMovie.revenue}")))
        }
    }

    @Test
    fun mainActivityTelevision() {
        onView(withText("TV Show")).perform(click())
        if (dataDummyTelevisionList != null) {
            onView(allOf(withId(R.id.rvTelevision), isDisplayed())).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    dataDummyTelevisionList.size
                )
            )
        }
    }

    @Test
    fun mainActivityTelevisionDetail() {
        onView(withText("TV Show")).perform(click())
        onView(allOf(withId(R.id.rvTelevision), isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        if (dataDummyTelevision != null) {
            onView(withId(R.id.imgShow)).perform(scrollTo()).check(matches(isCompletelyDisplayed()))
                .check(matches(withTagValue(CoreMatchers.equalTo(dataDummyTelevision.posterPath))))
            onView(withId(R.id.imgBackground)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withTagValue(CoreMatchers.equalTo(dataDummyTelevision.posterPath))))
            onView(withId(R.id.txtTitle)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(dataDummyTelevision.name)))
            onView(withId(R.id.txtSubTitle)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(
                    matches(
                        withText(
                            "${dataDummyTelevision.firstAirDate} | ${
                                dataDummyTelevision.episodeRunTime[0].div(
                                    60
                                )
                            }h ${dataDummyTelevision.episodeRunTime[0].rem(60)}m"
                        )
                    )
                )
            onView(withId(R.id.txtRating)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText("Rating: ${dataDummyTelevision.voteAverage?.times(10)}/100%")))
            onView(withId(R.id.txtQuote)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText("${dataDummyTelevision.season} Seasons, ${dataDummyTelevision.episode} Episodes")))
            onView(withId(R.id.txtOverview)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(dataDummyTelevision.overview)))
            onView(withId(R.id.txtStatus)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(dataDummyTelevision.status)))
            onView(withId(R.id.txtType)).perform(scrollTo())
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText("$ ${dataDummyTelevision.type}")))
        }
    }
}