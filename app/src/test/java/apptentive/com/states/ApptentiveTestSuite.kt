package apptentive.com.states

import apptentive.com.states.ui.StateVMTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    StateVMTest::class
)
class ApptentiveTestSuite