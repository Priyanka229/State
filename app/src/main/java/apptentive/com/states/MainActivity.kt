package apptentive.com.states

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import apptentive.com.states.databinding.ActivityMainBinding
import apptentive.com.states.ui.StateListFragment
import apptentive.com.states.ui.StateVM

class MainActivity : AppCompatActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // TODO: 1. load list of states from https://raw.githubusercontent.com/apptentive/states/master/states.json
//        // TODO: 2. display a simple list of state names in a RecyclerView. Should look like this:
//        //          - Alabama
//        //          - Arizona
//        //            ...
//        //          - Wyoming
//        // TODO: 3. show details for a selected state (when user clicks a row in the state list)
//        // TODO: 4. add unit/UI tests
//
//    }

    private lateinit var viewModel: StateVM

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** view model */
        viewModel = ViewModelProvider(this).get(StateVM::class.java)

        /** set up observers */
        setUpObservers()

        /** notify view-model about the app launch */
        viewModel.appLaunched()
    }

    private fun setUpObservers() {
        /** open list fragment observer */
        viewModel.openStateListScreen.observe(this) {
            loadStateListFragment()
        }
    }

    private fun loadStateListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, StateListFragment.newInstance())
            .commit()
    }
}