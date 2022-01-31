package by.seka.locations.ui.adapters.header

import android.content.Context
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import by.seka.locations.databinding.HeaderItemBinding
import by.seka.locations.ui.helper.SharedPreferencesHelper
import kotlinx.coroutines.*

class HeaderViewHolder(
    private val binding: HeaderItemBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {


    fun bind() {
        val category = SharedPreferencesHelper(context).getCategoryName()

        var editCategoryJob: Job? = null
        val scope = CoroutineScope(Dispatchers.IO)

        binding.editCategory.apply {

            setText(category)

            addTextChangedListener {

                editCategoryJob?.cancel()

                val newCategory = it.toString()

                editCategoryJob = scope.launch {

                    delay(1000)
                    SharedPreferencesHelper(context).setCategoryName(newCategory)
                }
            }
        }
    }
}