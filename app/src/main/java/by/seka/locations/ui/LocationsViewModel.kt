package by.seka.locations.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.Repository
import domain.model.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getLocations(): Flow<List<Location>> {

        return repository.getAll()
    }

    fun createLocation(location: Location) {
        viewModelScope.launch { repository.create(location) }
    }

    fun editLocation(locationName: String, id: Int) {
        viewModelScope.launch { repository.edit(locationName, id) }

    }
    fun deleteAll(){
        viewModelScope.launch { repository.deleteAll() }
    }

}