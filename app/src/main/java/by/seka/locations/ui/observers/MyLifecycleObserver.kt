package by.seka.locations.ui.observers

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MyLifecycleObserver(private val registry: ActivityResultRegistry) : DefaultLifecycleObserver {
    lateinit var getContent: ActivityResultLauncher<String>

    val imageUri = MutableSharedFlow<Uri>()
    override fun onCreate(owner: LifecycleOwner) {

        getContent = registry.register("key", owner, ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                owner.lifecycleScope.launch { imageUri.emit(uri) }
            }else return@register

        }
    }

    fun selectImage() {
        getContent.launch("image/*")

    }
}