package apptentive.com.states.utilities

import android.util.Log
import androidx.databinding.library.BuildConfig

object ApptentiveLogger {

    private const val TAG = "ApptentiveLogger"
    val DEBUG = BuildConfig.DEBUG

    fun d(tag: String = TAG, msg: String) {
        if (DEBUG) {
            Log.d(tag, msg)
        }
    }
}