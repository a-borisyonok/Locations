package by.seka.locations.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import by.seka.locations.R
import by.seka.locations.databinding.LocationsFragmentBinding
import by.seka.locations.ui.adapters.header.HeaderAdapter
import by.seka.locations.ui.adapters.locations.LocationsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import domain.model.Location
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LocationsFragment : Fragment() {

    companion object {
        fun newInstance() = LocationsFragment()
    }

    private var _binding: LocationsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LocationsViewModel by viewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = LocationsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val locationsAdapter = LocationsListAdapter(viewModel)
        val headerAdapter = HeaderAdapter()
        val concatAdapter = ConcatAdapter(headerAdapter, locationsAdapter)
        binding.apply {
            bottomNavi.selectedItemId = R.id.action_location
            recyclerView.adapter = concatAdapter
                //context?.let { LocationsListAdapter(viewModel) }
            //  ?.apply { setHasStableIds(true) }

            actionButton.setOnClickListener {
                // Log.i("fragment", adapter.toString())
                Log.i("fragment", viewModel.toString())
                viewModel.createLocation(Location(0, ""))
            }


            deleteAll.setOnClickListener { viewModel.deleteAll() }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.getLocations().collectLatest {
                locationsAdapter.submitList(it)
            }
        }
//        viewModel.getLocations().onEach(::render).launchIn(lifecycleScope)


    }

//    private fun render(location: List<Location>) {
//
//        adapter?.submitList(location)
//    }

}