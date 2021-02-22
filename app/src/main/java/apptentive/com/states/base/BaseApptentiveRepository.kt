package apptentive.com.states.base

import apptentive.com.states.network.ApptentiveNetworkController
import kotlinx.coroutines.CancellationException

open class BaseApptentiveRepository {
    @Throws(CancellationException::class)
    suspend inline fun <reified T>doGet(api: String, queryMap: Map<String, Any> = HashMap()) = ApptentiveNetworkController.doGet<T>(api, queryMap)
}