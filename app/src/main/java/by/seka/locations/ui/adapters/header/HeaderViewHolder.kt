package by.seka.locations.ui.adapters.header

import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import by.seka.locations.databinding.HeaderItemBinding

class HeaderViewHolder(
    private val binding: HeaderItemBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(flowerCount: Int) {
        binding.editCategory.addTextChangedListener {
            Log.i("header category", it.toString())
        }
    }
}