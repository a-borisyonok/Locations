package by.seka.locations.ui.adapters.locations


import android.net.Uri
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import by.seka.locations.databinding.LocationItemBinding
import by.seka.locations.domain.model.Location
import by.seka.locations.ui.LocationsViewModel
import by.seka.locations.ui.observers.MyLifecycleObserver
import by.seka.locations.ui.adapters.photos.PhotosAdapter
import by.seka.locations.util.EMPTY_STRING
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class LocationViewHolder(

    private val binding: LocationItemBinding,
    private val viewModel: LocationsViewModel,
    private val observer: MyLifecycleObserver

) : RecyclerView.ViewHolder(binding.root) {

    private val scope = CoroutineScope(Dispatchers.IO)

    private fun handlePickImage(item: Location) {

        observer.selectImage()
        updatePhotoList(item)
    }

    private fun updatePhotoList(item: Location) {
        scope.launch {

            observer.imageUri.collectLatest {
                if (it != Uri.EMPTY) {
                    val uri = it.toString()
                    delay(20)
                    Log.i("viewholderUri", uri)
                    if (item.photosList[0] == EMPTY_STRING) {
                        item.photosList.removeAt(0)
                    }
                    item.photosList.add(uri)
                    viewModel.editPhotoList(item.photosList, item.id)
                }
            }
        }
    }

    fun bind(item: Location) {


        binding.addPicture.setOnClickListener {

            handlePickImage(item)
        }


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

    companion object {
        const val PICK_IMAGE_MULTIPLE = 1
    }
}