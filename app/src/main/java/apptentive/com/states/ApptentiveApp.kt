package apptentive.com.states

import android.app.Application

class ApptentiveApp: Application() {
    companion object{
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}