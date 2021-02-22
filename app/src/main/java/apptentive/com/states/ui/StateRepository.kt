package apptentive.com.states.ui

import apptentive.com.states.base.BaseApptentiveRepository
import apptentive.com.states.beans.State
import apptentive.com.states.network.ApptentiveNetworkConstants.STATES
import apptentive.com.states.network.ApptentiveResponse
import apptentive.com.states.utilities.OpenForTesting

@OpenForTesting
class StateRepository: BaseApptentiveRepository() {
    suspend fun getStates(): ApptentiveResponse<List<State>> = doGet(STATES)
}