package apptentive.com.states.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import apptentive.com.states.base.BaseApptentiveVM
import apptentive.com.states.beans.State
import apptentive.com.states.utilities.SingleLiveEvent
import kotlinx.coroutines.launch

class StateVM(app: Application) : BaseApptentiveVM<StateRepository, StateInteractor>(app) {
    val stateListLiveData = MutableLiveData<List<State>>()
    val openStateListScreen = SingleLiveEvent<Nothing>()

    init {
        repository = StateRepository()
        interactor = StateInteractor()
    }
    fun stateListScreenLaunched() {
        fetchStates()
    }

    private fun fetchStates() {
        /** fetch the signed in user response */
        viewModelScope.launch(coroutineContext) {

            /** show loader */
            uiBlockingLoaderLiveEvent.postValue(true)

            /** reading from server */
            val output = repository.getStates()
            if (output.data != null) {
                stateListLiveData.postValue(output.data)
            } else {
                output.error?.msg?.let { showMsgLiveEvent.postValue(it) }
            }

            /** hide loader */
            uiBlockingLoaderLiveEvent.postValue(false)
        }
    }

    fun appLaunched() {
        /** open state list page */
        openStateListScreen.postValue(null)
    }
}