package by.seka.locations.ui.adapters.locations

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.seka.locations.databinding.LocationItemBinding
import by.seka.locations.domain.model.Location
import by.seka.locations.ui.observers.MyLifecycleObserver

class LocationsListAdapter(
    private val observer: MyLifecycleObserver,
    private val deleteButton: ImageButton,
    private val adapterOnclick: (Location, Any) -> Unit,

    ) :
    ListAdapter<Location, LocationViewHolder>(itemComparator) {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {

val context = parent.context.applicationContext
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LocationItemBinding.inflate(layoutInflater, parent, false)
        return LocationViewHolder(binding, adapterOnclick, observer, deleteButton, context)
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


