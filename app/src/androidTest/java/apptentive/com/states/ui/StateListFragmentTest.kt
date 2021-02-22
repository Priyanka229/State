package apptentive.com.states.ui

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import apptentive.com.states.MainActivity
import apptentive.com.states.R
import apptentive.com.states.utilities.EspressoIdlingResource
import org.hamcrest.Matcher
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class StateListFragmentTest {
    @get: Rule
    val activityScenario = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    /** check if recycler view is visible */
    @Test
    fun isRecyclerViewVisible() {
        onView(withId(R.id.state_rv)).check(matches(isDisplayed()))
    }

    /** click on any item should open detail fragment */
    @Test
    fun test_selectListItem_isDetailFragmentVisible() {
        val itemPosition = 15

        onView(withId(R.id.state_rv))
            .perform(actionOnItemAtPosition<StateListAdapter.StateViewHolder>(itemPosition, click()))

        onView(withId(R.id.detail_cl)).check(matches(isDisplayed()))
    }

    /** click list item, move to detail fragment and press back */
    @Test
    fun test_backNavigation_toStateListFragment() {
        var text = ""
        val itemPosition = 15

        /** get the view from recyclerview at itemPosition */
        onView(withId(R.id.state_rv))
            .perform(
                actionOnItemAtPosition<StateListAdapter.StateViewHolder>(itemPosition,
                    object : ViewAction {
                        override fun getConstraints(): Matcher<View>? = null
                        override fun getDescription(): String? = null
                        override fun perform(uiController: UiController?, view: View?) {
                            val v = view?.findViewById<View>(R.id.name_tv) as TextView
                            text = v.text.toString()
                        }
                    })
            )

        /** click on itemPosition to open the detail page*/
        onView(withId(R.id.state_rv))
            .perform(actionOnItemAtPosition<StateListAdapter.StateViewHolder>(itemPosition, click()))

        /** match the text on detail page */
        onView(withId(R.id.state_name_tv)).check(matches(withText(text)))

        /** press back */
        pressBack()

        /** verify if we are on state list page */
        onView(withId(R.id.state_rv)).check(matches(isDisplayed()))
    }
}