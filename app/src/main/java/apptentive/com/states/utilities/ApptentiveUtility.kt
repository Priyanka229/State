package apptentive.com.states.utilities

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ApptentiveUtility {

    inline fun <reified T> toParse(jsonString: String?): T? {
        return try {
            Gson().fromJson<T>(jsonString, object : TypeToken<T?>() {}.type)
        } catch (ex: Exception) {
            null
        }
    }
}