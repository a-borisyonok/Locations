package by.seka.locations.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.seka.locations.databinding.FullScreenPhotoBinding
import by.seka.locations.ui.util.PATH
import com.bumptech.glide.Glide


class FullScreenPhoto : Fragment() {

    private var _binding: FullScreenPhotoBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FullScreenPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val imagePath = arguments?.getString(PATH)

        Glide.with(this)
            .load(imagePath)
            .into(binding.ivFullscreenPhoto)

    }
}