package apptentive.com.states.ui

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import apptentive.com.states.base.BaseApptentiveVM
import apptentive.com.states.beans.State
import apptentive.com.states.roomdb.StateDataBase
import apptentive.com.states.utilities.EspressoIdlingResource
import apptentive.com.states.utilities.SingleLiveEvent
import kotlinx.coroutines.launch

class StateVM(val app: Application) : BaseApptentiveVM<StateRepository, StateInteractor>(app) {
    val stateListLiveData = MediatorLiveData<List<State>>()
    val openStateListScreen = SingleLiveEvent<Nothing>()
    val openStateDetailScreen = SingleLiveEvent<State>()

    var states: List<State>? = null

    init {
        repository = StateRepository()
        interactor = StateInteractor()
    }

    fun stateListScreenLaunched() {
        databaseObserver()
        fetchStates()
    }

    private fun databaseObserver() {
        val stateLiveData = StateDataBase.getInstance(app).stateDao().getStates()
        stateListLiveData.addSource(stateLiveData) {
            val filterList = interactor.getFilteredList(it, false)
            stateListLiveData.postValue(filterList)
        }
    }
    private fun fetchStates() {
        /** fetch the signed in user response */
        viewModelScope.launch(coroutineContext) {
            EspressoIdlingResource.increment()

            /** show loader */
            uiBlockingLoaderLiveEvent.postValue(true)

            /** reading from server */
            val output = repository.getStates()
            states = output.data

            if (output.data != null) {
                val states = output.data

                StateDataBase.getInstance(app).stateDao().insert(states)

            } else {
                output.error?.msg?.let { showMsgLiveEvent.postValue(it) }
            }

            /** hide loader */
            uiBlockingLoaderLiveEvent.postValue(false)

            EspressoIdlingResource.decrement()
        }
    }

    fun appLaunched() {
        /** open state list page */
        openStateListScreen.postValue(null)
    }

    fun onStateItemClick(state: State) {
        openStateDetailScreen.postValue(state)
    }

    fun filterStateChanged(b: Boolean) {
        val filterList = interactor.getFilteredList(states, b)
        if (filterList != null) {
            stateListLiveData.postValue(filterList)
        }
    }
}