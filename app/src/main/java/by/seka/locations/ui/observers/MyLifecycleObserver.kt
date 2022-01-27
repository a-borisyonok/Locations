package by.seka.locations.ui.observers

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MyLifecycleObserver(private val registry: ActivityResultRegistry) : DefaultLifecycleObserver {
    lateinit var getContent: ActivityResultLauncher<String>

    var imageUri = MutableStateFlow(Uri.EMPTY)
    override fun onCreate(owner: LifecycleOwner) {

        getContent = registry.register("key", owner, ActivityResultContracts.GetContent()) { uri ->
            owner.lifecycleScope.launch { imageUri.emit(uri) }
        }

    }

    fun selectImage() {
        getContent.launch("image/*")

    }
}