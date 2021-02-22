package apptentive.com.states.network

import apptentive.com.states.ApptentiveApp
import apptentive.com.states.R
import apptentive.com.states.network.ApptentiveNetworkConnector.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class ApptentiveNetworkInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        if (!isNetworkAvailable(ApptentiveApp.instance)) {
            throw NoNetworkException()
        }
        return chain.proceed(request)
    }

    class NoNetworkException : IOException() {
        override val message: String
            get() = ApptentiveApp.instance.resources.getString(R.string.network_unavailable_message)
    }
}