package by.seka.locations.ui.adapters.locations


import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import by.seka.locations.databinding.LocationItemBinding
import by.seka.locations.ui.LocationsViewModel
import by.seka.locations.ui.adapters.photos.PhotosAdapter
import domain.model.Location


class LocationViewHolder(

    private val binding: LocationItemBinding,
    private val viewModel: LocationsViewModel

) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: Location) {

        val adapter = PhotosAdapter()
        binding.rvLocationPhotos.adapter = adapter

        binding.editLocation.apply {

            var newName = item.locationName

            setText(item.locationName)
            setSelection(item.locationName.length)

            addTextChangedListener() { newName = it.toString() }

            setOnFocusChangeListener { _, hasFocus ->

                if (!hasFocus) {
                    viewModel.editLocation(
                        newName,
                        item.id
                    )
                }
            }
        }
    }
}