package by.seka.locations.ui.adapters.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.seka.locations.databinding.PhotoItemBinding
import kotlin.coroutines.coroutineContext

class PhotosAdapter(

    private val adapterOnLongClick: (Any, Long) -> Unit
) :
    ListAdapter<String, PhotosViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val context = parent.context.applicationContext
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PhotoItemBinding.inflate(layoutInflater, parent, false)
        return PhotosViewHolder(binding, context, adapterOnLongClick)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {


        holder.bind(getItem(position), position)
    }


    private companion object {

        private val itemComparator = object : DiffUtil.ItemCallback<String>() {


            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return  oldItem.length == newItem.length
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
               return  oldItem == newItem
            }
        }
    }

}