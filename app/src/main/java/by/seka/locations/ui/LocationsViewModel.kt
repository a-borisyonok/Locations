package by.seka.locations.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import by.seka.locations.domain.Repository
import by.seka.locations.domain.model.Location
import kotlinx.coroutines.flow.Flow

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
        viewModelScope.launch { repository.editName(locationName, id) }


    }
    fun editPhotoList(photoList: MutableList<String>, id: Int ){
        viewModelScope.launch { repository.editPhotoList(photoList, id) }
    }
    fun removeAllPhotos(id: Int){
        viewModelScope.launch { repository.removeAllPhotos(id) }
    }
    fun deleteAll(){
        viewModelScope.launch { repository.deleteAll() }
    }

}