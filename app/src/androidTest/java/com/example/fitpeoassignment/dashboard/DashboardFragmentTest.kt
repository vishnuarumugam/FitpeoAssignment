package com.example.fitpeoassignment.dashboard

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fitpeoassignment.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DashboardFragmentTest {



    @Before
    fun setup() {
        // Launch the DashboardFragment in a container
        launchFragmentInContainer<DashboardFragment>()
    }
    @Test
    fun testDashboardFragmentInitialState() {
        // Verify the initial state of the DashboardFragment
        Thread.sleep(30000)
        onView(withId(R.id.rv_album))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_album))
            .check(matches(hasChildCount(2)))


        //Perform click to Recycler view
        onView(withId(R.id.rv_album))
            .check(matches(isDisplayed()))
            .perform(ViewActions.click())
        Thread.sleep(2000)

    }


}