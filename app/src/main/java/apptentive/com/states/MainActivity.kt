package apptentive.com.states

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: 1. load list of states from https://raw.githubusercontent.com/apptentive/states/master/states.json
        // TODO: 2. display a simple list of state names in a RecyclerView. Should look like this:
        //          - Alabama
        //          - Arizona
        //            ...
        //          - Wyoming
        // TODO: 3. show details for a selected state (when user clicks a row in the state list)
        // TODO: 4. add unit/UI tests
    }
}