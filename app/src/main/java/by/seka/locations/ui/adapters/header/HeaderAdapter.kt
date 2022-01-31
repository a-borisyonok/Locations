package by.seka.locations.ui.adapters.header

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.seka.locations.databinding.HeaderItemBinding

class HeaderAdapter : RecyclerView.Adapter<HeaderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val context = parent.context.applicationContext
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HeaderItemBinding.inflate(layoutInflater, parent, false)
        return HeaderViewHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind()
    }
}