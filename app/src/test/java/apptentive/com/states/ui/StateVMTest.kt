package apptentive.com.states.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import apptentive.com.states.ApptentiveApp
import apptentive.com.states.beans.State
import apptentive.com.states.network.ApptentiveError
import apptentive.com.states.network.ApptentiveResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StateVMTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: StateRepository

    @Mock
    private lateinit var interactor: StateInteractor

    @Mock
    private lateinit var msgLiveEventObserver: Observer<String>
    @Mock
    private lateinit var stateListLiveDataObserver: Observer<List<State>>

    @Before
    fun setUp() {
//        MockitoAnnotations.initMocks(this)
        // do something if required
    }

    /** test the serve response in case of success */
    @Test
    fun `test the server response in case of success`() {
        testCoroutineRule.runBlockingTest {
            doReturn(ApptentiveResponse<List<State>>(requestType = "test", data = emptyList()))
                .`when`(repository)
                .getStates()

            val output = repository.getStates().data
            stateListLiveDataObserver.onChanged(output)

            val applicationMock = ApptentiveApp()
            val viewModel = StateVM(applicationMock)
            viewModel.stateListLiveData.observeForever(stateListLiveDataObserver)
            verify(repository, times(1)).getStates()
            verify(stateListLiveDataObserver).onChanged(emptyList())
            viewModel.stateListLiveData.removeObserver(stateListLiveDataObserver)
        }
    }

    /** test the serve response in case of failure */
    @Test
    fun `test the server response in case of failure`() {
        testCoroutineRule.runBlockingTest {
            doReturn(ApptentiveResponse<List<State>>(requestType = "test", error = ApptentiveError("something went wrong")))
                .`when`(repository)
                .getStates()

            val output = repository.getStates().error?.msg
            msgLiveEventObserver.onChanged(output)

            val applicationMock = ApptentiveApp()
            val viewModel = StateVM(applicationMock)
            viewModel.showMsgLiveEvent.observeForever(msgLiveEventObserver)
            verify(repository).getStates()
            verify(msgLiveEventObserver).onChanged("something went wrong")
            viewModel.showMsgLiveEvent.removeObserver(msgLiveEventObserver)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }
}