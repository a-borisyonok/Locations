package by.seka.locations.ui.adapters.locations


import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import by.seka.locations.databinding.LocationItemBinding
import by.seka.locations.domain.model.Location
import by.seka.locations.ui.LocationsFragment
import by.seka.locations.ui.adapters.`interface`.PhotoLongClickListener
import by.seka.locations.ui.adapters.photos.PhotosAdapter
import by.seka.locations.ui.helper.ImageHelper
import by.seka.locations.ui.observers.MyLifecycleObserver
import by.seka.locations.ui.util.EMPTY_STRING
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import java.util.*


class LocationViewHolder(

    private val binding: LocationItemBinding,
    private val adapterOnClick: (Location, Any) -> Unit,
    private val observer: MyLifecycleObserver,
    private val deleteButton: ImageButton,
    private val context: Context,
    parentFragment: LocationsFragment

) : RecyclerView.ViewHolder(binding.root) {

    private val photosAdapter =
        PhotosAdapter(parentFragment) { item, key -> doClick(item, key) }
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val photosForDeleteList: SortedMap<Long, String> = sortedMapOf()


    fun bind(item: Location) {

        val photoList = item.photosList ?: mutableListOf()

        PhotoLongClickListener.Deletable.deletable = false

        handleEditLocationName(item)

        with(binding) {
            rvLocationPhotos.adapter = photosAdapter

            photosAdapter.submitList(item.photosList)

            addPicture.setOnClickListener {

                handlePickImage(item)
            }
        }
        deleteButton.setOnClickListener {

            handlePhotoDeletion(item, photoList)
        }
    }

    private fun handlePhotoDeletion(item: Location, photoList: MutableList<String>) {

        photosForDeleteList.forEach {
            if (it.value.isNotEmpty()) {
                photoList[it.key.toInt()] = EMPTY_STRING
            }
        }

        val newPhotoList = mutableListOf<String>()

        photoList.forEach {
            if (it.isNotEmpty()) {
                newPhotoList.add(it)
            }
        }

        adapterOnClick(item, newPhotoList)

        PhotoLongClickListener.Deletable.deletable = false

        deleteImagesFromStorage(newPhotoList)

        photosForDeleteList.clear()

        deleteButton.visibility = View.GONE

        bindingAdapter?.notifyItemChanged(bindingAdapterPosition)
    }

    private fun deleteImagesFromStorage(newPhotoList: MutableList<String>) {
        ioScope.launch {
            newPhotoList.forEach {
                ImageHelper().deleteFile(it)
            }
        }
    }

    private fun handleEditLocationName(item: Location) {
        binding.editLocation.apply {

            var newName: String
            var editTextJob: Job? = null

            setText(item.locationName)
            setSelection(item.locationName.length)

            addTextChangedListener {

                editTextJob?.cancel()

                newName = it.toString()

                editTextJob = mainScope.launch {
                    delay(1000)
                    adapterOnClick(item, newName)
                }
            }
        }
    }

    private fun handlePickImage(item: Location) {
        observer.selectImage()
        updatePhotoList(item)
    }

    private fun updatePhotoList(item: Location) {

        val canAddPhoto = MutableStateFlow(false)
        var uri: Uri
        var filepath: String = EMPTY_STRING

        mainScope.launch {

            observer.imageUri.collectLatest {

                if (it != Uri.EMPTY && it != null) {
                    uri = it


                    if (item.photosList?.get(0) == EMPTY_STRING) {
                        item.photosList.removeAt(0)
                    }

                    ioScope.launch {
                        filepath = ImageHelper().saveBitmapIntoStorage(context, uri)
                        canAddPhoto.emit(true)
                    }
                }
            }
        }

        mainScope.launch {
            canAddPhoto.collectLatest {
                if (it) {
                    delay(500)
                    addPhoto(item, filepath)
                }
            }
        }
    }


    private fun addPhoto(item: Location, uri: String) {
        item.photosList?.add(uri)
        item.photosList?.let { adapterOnClick(item, it) }
        photosAdapter.notifyDataSetChanged()

    }


    private fun doClick(item: Any, key: Long) {

        when (item::class) {

            String::class -> {
                photosForDeleteList[key] = item.toString()
            }

            Boolean::class -> {
                PhotoLongClickListener.Deletable.deletable = true
                deleteButton.visibility = View.VISIBLE
                photosAdapter.notifyDataSetChanged()
            }
        }
    }
}

