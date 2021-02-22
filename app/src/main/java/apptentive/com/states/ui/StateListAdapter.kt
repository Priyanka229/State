package apptentive.com.states.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import apptentive.com.states.beans.State
import apptentive.com.states.databinding.AdapterItemStateBinding

class StateListAdapter(val itemClickListener: OnItemClickListener? = null) : ListAdapter<State, StateListAdapter.StateViewHolder>(
    DiffCallback()
) {

    interface OnItemClickListener {
        fun onItemClick(state: State)
    }

    class DiffCallback : DiffUtil.ItemCallback<State>() {
        override fun areContentsTheSame(oldItem: State, newItem: State) = oldItem == newItem
        override fun areItemsTheSame(oldItem: State, newItem: State) = oldItem.name == newItem.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateViewHolder {
        val binding = AdapterItemStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StateViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class StateViewHolder(private val binding: AdapterItemStateBinding, private val itemClickListener: OnItemClickListener? = null): RecyclerView.ViewHolder(binding.root) {
        fun bind(state: State) {
            binding.apply {
                nameTv.text = state.name

                root.setOnClickListener {
                    itemClickListener?.onItemClick(state)
                }
            }
        }
    }
}