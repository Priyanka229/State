package apptentive.com.states.ui

import apptentive.com.states.base.BaseApptentiveInteractor
import apptentive.com.states.beans.State
import apptentive.com.states.utilities.OpenForTesting

@OpenForTesting
class StateInteractor: BaseApptentiveInteractor() {
    fun getFilteredList(states: List<State>?, filterState: Boolean): List<State>? {
        return if (!filterState) {
            states
        } else {
            states?.filter {
                it.isFav == filterState
            }
        }
    }
}