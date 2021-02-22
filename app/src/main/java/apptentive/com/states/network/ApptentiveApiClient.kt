package apptentive.com.states.network

import apptentive.com.states.utilities.ApptentiveLogger
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

object ApptentiveApiClient {
    var headers =  ConcurrentHashMap<String, String>()

    private val httpClient =
            OkHttpClient.Builder()
                    .addInterceptor(Interceptor { chain ->
                        val original = chain.request()

                        val request = original.newBuilder()
                            .headers(getHeaders(headers))
                            .method(original.method, original.body)
                            .build()
                        chain.proceed(request)
                    })
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(ApptentiveNetworkInterceptor())
                    .apply {
                        if (ApptentiveLogger.DEBUG) addInterceptor(
                            ApptentiveNetworkLoggingIntercepter()
                        )
                    }
                    .retryOnConnectionFailure(true)
                    .build()

    private val retrofit  = Retrofit.Builder()
            .addConverterFactory(ApptentiveNullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApptentiveNetworkConstants.URL)
            .client(httpClient)
            .build()

    var service: ApptentiveApiService = retrofit.create(ApptentiveApiService::class.java)

    private fun getHeaders(map: ConcurrentHashMap<String, String>): Headers {
        val builder = Headers.Builder()
        for ((key, value) in map) {
            builder.add(key, value)
        }
        return builder.build()
    }
}