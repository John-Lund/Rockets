package com.example.android.rockets.resultspage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.rockets.databinding.ResultsListItemBinding
import com.example.android.rockets.network.Rocket

class ResultsAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Rocket, ResultsAdapter.ResultsViewHolder>(DiffCallback) {

    class ResultsViewHolder(private var binding: ResultsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rocket: Rocket) {
            binding.rocket = rocket
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        return ResultsViewHolder(
            ResultsListItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onClickListener.onClick(getItem(position)) }
        holder.bind(getItem(position))
    }

    // not really using this as rocket data set is small and doesn't vary
    companion object DiffCallback : DiffUtil.ItemCallback<Rocket>() {
        override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (rocket: Rocket) -> Unit) {
        fun onClick(rocket: Rocket) = clickListener(rocket)
    }
}