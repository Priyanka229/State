package apptentive.com.states.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import apptentive.com.states.beans.State

@Database(entities = [State::class], version = 1)
abstract class StateDataBase : RoomDatabase() {

    abstract fun stateDao(): StateDao

    companion object {
        private var instance: StateDataBase? = null

        @Synchronized
        fun getInstance(context: Context): StateDataBase {
            if(instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, StateDataBase::class.java, "state_database.db").build()
            }
            return instance!!
        }
    }
}