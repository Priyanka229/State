package apptentive.com.states.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import apptentive.com.states.base.BaseApptentiveFragment
import apptentive.com.states.databinding.FragmentStateListBinding

class StateListFragment : BaseApptentiveFragment() {
    lateinit var binding: FragmentStateListBinding

    companion object {
        fun newInstance() = StateListFragment()
    }

    private lateinit var viewModel: StateVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentStateListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** view model */
        viewModel = ViewModelProvider(activity!!).get(StateVM::class.java)

        /** resume the view-model */
        viewModel.stateListScreenLaunched()
    }
}