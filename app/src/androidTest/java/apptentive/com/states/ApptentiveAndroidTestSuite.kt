package apptentive.com.states

import apptentive.com.states.ui.StateDetailFragmentTest
import apptentive.com.states.ui.StateListFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    StateListFragmentTest::class,
    StateDetailFragmentTest::class,
    MainActivityTest::class
)
class ApptentiveAndroidTestSuite