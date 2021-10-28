package com.c1ctech.jetpackpagingexp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.c1ctech.jetpackpagingexp.data.models.Passenger
import com.c1ctech.jetpackpagingexp.databinding.ItemPassengerBinding

class PassengersAdapter :
    PagingDataAdapter<Passenger, PassengersAdapter.PassengersViewHolder>(PassengersComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PassengersViewHolder {
        return PassengersViewHolder(
            ItemPassengerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PassengersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindPassenger(it) }
    }

    inner class PassengersViewHolder(private val binding: ItemPassengerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPassenger(item: Passenger) = with(binding) {
            imageViewAirlinesLogo.loadImage(item.airline.get(0).logo)
            textViewHeadquarters.text = item.airline.get(0).head_quaters
            textViewNameWithTrips.text = "${item.name}, ${item.trips} Trips"
        }
    }

    object PassengersComparator : DiffUtil.ItemCallback<Passenger>() {
        override fun areItemsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem == newItem
        }
    }
}