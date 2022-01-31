package by.seka.locations.ui.adapters.photos

import android.content.Context
import android.net.Uri
import android.view.View
import android.view.View.OnLongClickListener
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import by.seka.locations.databinding.PhotoItemBinding
import by.seka.locations.ui.adapters.`interface`.PhotoLongClickListener
import by.seka.locations.ui.util.EMPTY_STRING
import com.bumptech.glide.Glide
import java.io.File


class PhotosViewHolder(
    private val binding: PhotoItemBinding,
    private val context: Context,
    private val adapterOnPhotoClick: (Any, Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var photoIsChecked: Boolean = false
    private var checkedPhoto: String = EMPTY_STRING


    fun bind(item: String, position: Int) {

        if (item.isNotEmpty()) {
            Glide.with(context)
                .load(item)
                .into(binding.ivPhoto)
        }
        val deletable: Boolean = PhotoLongClickListener.Deletable.deletable

        if (deletable) {
            setCheckboxVisible()
        }

        with(binding) {

            photoItem.setOnLongClickListener(longClickListener())

            if (photoItem.isClickable) {
                photoItem.setOnClickListener {
                    checkOrUncheckPhoto(item)
                    adapterOnPhotoClick(checkedPhoto, position.toLong())
                }

                checkbox.setOnClickListener {
                    checkOrUncheckPhoto(item)
                    adapterOnPhotoClick(checkedPhoto, position.toLong())
                }
            }
        }
    }

    private fun longClickListener(): OnLongClickListener {
        return OnLongClickListener {
            setCheckboxVisible()
            binding.photoItem.isClickable = true
            adapterOnPhotoClick(true, -1L)
            true
        }
    }

    private fun checkOrUncheckPhoto(item: String) {

        with(binding) {
            if (!photoIsChecked) {

                photoItem.isChecked = true
                checkbox.isChecked = true
                photoIsChecked = true
                checkedPhoto = item

            } else {
                photoItem.isChecked = false
                checkbox.isChecked = false
                photoIsChecked = false
                checkedPhoto = EMPTY_STRING
            }
        }
    }


    private fun setCheckboxVisible() {

        with(binding) {
            photoItem.isCheckable = true
            photoItem.isClickable = true
            photoItem.isFocusable = true
            checkbox.visibility = View.VISIBLE
        }
    }
}
