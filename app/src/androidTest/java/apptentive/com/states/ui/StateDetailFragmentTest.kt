package apptentive.com.states.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import apptentive.com.states.R
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class StateDetailFragmentTest {
    /** check if detail page is visible */
    @Test
    fun isStateDetailPageVisible() {
        val scenario = launchFragmentInContainer<StateDetailFragment>()
        Espresso.onView(ViewMatchers.withId(R.id.detail_cl))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}