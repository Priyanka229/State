package apptentive.com.states.network

import apptentive.com.states.ApptentiveApp
import apptentive.com.states.R
import apptentive.com.states.utilities.ApptentiveUtility
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.internal.LinkedTreeMap
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ApptentiveNetworkController {
    suspend inline fun <reified T> doGet(api: String, queryMap: Map<String, Any> = HashMap()): ApptentiveResponse<T> {
        val apiResponse = ApptentiveApiClient.service.get<LinkedTreeMap<String, String>>(api, queryMap)
        return processApiResponse(api, apiResponse)
    }

    inline fun <reified T> processApiResponse(requestType: String, response: Response<LinkedTreeMap<String, String>>): ApptentiveResponse<T> {
        return try {
            handleApiResponse(requestType, response)
        } catch (ex: Exception) {
            val error = handleApiCallExceptions(ex)
            ApptentiveResponse(requestType = requestType, error = error)
        }
    }

    inline fun <reified T> handleApiResponse(requestType: String, response: Response<LinkedTreeMap<String, String>>): ApptentiveResponse<T> {
        val returnValue: ApptentiveResponse<T>

        when {
            response.isSuccessful -> {
                val gsonStr = Gson().toJson(response.body())
                val responsePOJO = ApptentiveUtility.toParse<T>(gsonStr)
                returnValue = ApptentiveResponse(requestType = requestType, data = responsePOJO)
            }

            else -> {
                var error: ApptentiveError? = null
                val errorString = if (response.errorBody() != null) response.errorBody()!!.string() else null
                if (errorString != null) {
                    error = Gson().fromJson(errorString, ApptentiveError::class.java)
                }

                if (error == null) { error = ApptentiveError(msg = ApptentiveApp.instance.resources.getString(
                    R.string.something_went_wrong)) }

                returnValue = ApptentiveResponse(requestType = requestType, error = error)
            }
        }

        return returnValue
    }

    fun handleApiCallExceptions(ex: Exception): ApptentiveError {
        return when(ex) {
            is ApptentiveNetworkInterceptor.NoNetworkException -> ApptentiveError(msg = ApptentiveApp.instance.resources.getString(R.string.network_unavailable_message))
            is JsonParseException -> ApptentiveError(msg = ApptentiveApp.instance.resources.getString(R.string.parse_error))
            is SocketTimeoutException -> ApptentiveError(msg = ApptentiveApp.instance.resources.getString(R.string.socket_time_out))
            is UnknownHostException -> ApptentiveError(msg = ApptentiveApp.instance.resources.getString(R.string.something_went_wrong))
            else -> throw ex
        }
    }
}

data class ApptentiveResponse <T> (
    val requestType: String,
    val data: T? = null,
    val error: ApptentiveError? = null
)
data class ApptentiveError(
    val msg: String
)
