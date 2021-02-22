package apptentive.com.states.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import apptentive.com.states.base.BaseApptentiveFragment
import apptentive.com.states.beans.State
import apptentive.com.states.databinding.FragmentStateDetailBinding
import apptentive.com.states.utilities.ApptentiveUtility

class StateDetailFragment : BaseApptentiveFragment() {
    lateinit var binding: FragmentStateDetailBinding

    companion object {
        fun newInstance() = StateDetailFragment()
        const val ARGUMENT_STATE = "ARGUMENT_STATE"
    }

    private lateinit var viewModel: StateVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentStateDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /** view model */
        viewModel = ViewModelProvider(activity!!).get(StateVM::class.java)

        arguments?.getParcelable<State>(ARGUMENT_STATE)?.let { setUpState(it) }
    }

    private fun setUpState(state: State) {
        binding.apply {
            /** set state name */
            stateNameTv.apply {
                val stateName = "${state.name}"
                text = stateName
                visibility = if (state.name.isNullOrBlank()) View.GONE else View.VISIBLE
            }

            /** set capital name */
            val capitalName = "Capital :  ${state.capital}"
            capitalTv.apply {
                text = capitalName
                visibility = if (state.capital.isNullOrBlank()) View.GONE else View.VISIBLE
            }

            /** set largest city name */
            val largestCityName = "Largest City :  ${state.largest_city}"
            largestCityTv.apply {
                text = largestCityName
                visibility = if (state.largest_city.isNullOrBlank()) View.GONE else View.VISIBLE
            }

            /** set est date */
            val establishedDate = "Est Date :  ${ApptentiveUtility.getDateStr(state.established_date)}"
            establishedDateTv.apply {
                text = establishedDate
                visibility = if (state.established_date.isNullOrBlank()) View.GONE else View.VISIBLE
            }

            /** set population */
            val population = "Population :  ${state.population}"
            populationTv.apply {
                text = population
                visibility = if (state.population == null) View.GONE else View.VISIBLE
            }


            /** set area */
            val totalAreaKm2 = "Total Area :  ${state.total_area_km2}"
            val landAreaKm2 = "Land Area :  ${state.land_area_km2}"
            val waterAreaKm2 = "Water Area :  ${state.water_area_km2}"

            totalAreaKm2Tv.apply {
                text = totalAreaKm2
                visibility = if (state.total_area_km2 == null) View.GONE else View.VISIBLE
            }
            landAreaKm2Tv.apply {
                text = landAreaKm2
                visibility = if (state.land_area_km2 == null) View.GONE else View.VISIBLE
            }
            waterAreaKm2Tv.apply {
                text = waterAreaKm2
                visibility = if (state.water_area_km2 == null) View.GONE else View.VISIBLE
            }

            /** set representatives no. */
            val numberOfReps = "Representatives no. :  ${state.number_of_reps}"
            numberOfRepsTv.apply {
                text = numberOfReps
                visibility = if (state.number_of_reps == null) View.GONE else View.VISIBLE
            }
        }
    }
}