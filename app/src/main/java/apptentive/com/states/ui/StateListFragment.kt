package apptentive.com.states.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import apptentive.com.states.base.BaseApptentiveFragment
import apptentive.com.states.beans.State
import apptentive.com.states.databinding.FragmentStateListBinding

class StateListFragment : BaseApptentiveFragment() {
    lateinit var binding: FragmentStateListBinding

    private lateinit var stateAdapter: StateListAdapter
    companion object {
        fun newInstance() = StateListFragment()
    }

    private lateinit var viewModel: StateVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        stateAdapter = StateListAdapter(itemClickListener = object : StateListAdapter.OnItemClickListener {
            override fun onItemClick(state: State) {
                viewModel.onStateItemClick(state)
            }
        })
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentStateListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** set up state list view */
        binding.apply {
            stateRv.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = stateAdapter
            }
        }

        /** view model */
        viewModel = ViewModelProvider(activity!!).get(StateVM::class.java)

        /** observers */
        setUpObservers()

        /** resume the view-model */
        viewModel.stateListScreenLaunched()
    }

    private fun setUpObservers() {
        /** data observer */
        viewModel.stateListLiveData.observe(viewLifecycleOwner) {
            stateAdapter.submitList(it)
        }

        /** loader observer */
        viewModel.uiBlockingLoaderLiveEvent.observe(viewLifecycleOwner) { showProgress ->
            binding.progress.visibility = if (showProgress) View.VISIBLE else View.GONE
        }

        /** msg observer */
        viewModel.showMsgLiveEvent.observe(viewLifecycleOwner) {
            showMsg(it)
        }
    }
}