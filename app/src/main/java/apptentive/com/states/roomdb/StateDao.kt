package apptentive.com.states.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import apptentive.com.states.beans.State

@Dao
interface StateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(states: List<State>)

    @Query("select * from state_table order by name")
    fun getStates() : LiveData<List<State>>
}