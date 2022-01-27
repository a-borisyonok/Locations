package by.seka.locations.ui.adapters.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.seka.locations.databinding.LocationItemBinding
import by.seka.locations.domain.model.Location
import by.seka.locations.ui.LocationsViewModel
import by.seka.locations.ui.observers.MyLifecycleObserver

class LocationsListAdapter(
    private val viewModel: LocationsViewModel,
    private val observer: MyLifecycleObserver
) :
    ListAdapter<Location, LocationViewHolder>(itemComparator), DefaultLifecycleObserver {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {

         val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LocationItemBinding.inflate(layoutInflater, parent, false)
        return LocationViewHolder(binding, viewModel, observer)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = getItem(position)

        holder.bind(location)
    }

    private companion object {

        private val itemComparator = object : DiffUtil.ItemCallback<Location>() {

            override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
                return oldItem == newItem
            }
        }
    }
}


