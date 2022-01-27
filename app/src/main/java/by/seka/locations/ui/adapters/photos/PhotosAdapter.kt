package by.seka.locations.ui.adapters.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.seka.locations.databinding.PhotoItemBinding
import by.seka.locations.domain.model.Location

class PhotosAdapter() :
    RecyclerView.Adapter<PhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PhotoItemBinding.inflate(layoutInflater, parent, false)
        return PhotosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {


        holder.bind(itemCount)
    }

    override fun getItemCount(): Int {
        return 6
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