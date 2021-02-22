package apptentive.com.states.utilities

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat

object ApptentiveUtility {

    inline fun <reified T> toParse(jsonString: String?): T? {
        return try {
            Gson().fromJson<T>(jsonString, object : TypeToken<T?>() {}.type)
        } catch (ex: Exception) {
            null
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getDateStr(inputDate: String?) : String? {
        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
            val date = dateFormat.parse(inputDate!!)

            date!!.toString()
        } catch (ex: Exception) {
            null
        }
    }
}