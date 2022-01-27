package by.seka.locations.domain

import android.util.Log
import by.seka.locations.data.LocationsDao
import by.seka.locations.domain.model.Location
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class Repository @Inject constructor (private val dao: LocationsDao) {

    fun getAll(): Flow<List<Location>> {
Log.i("repository", dao.getAll().toString())

       return dao.getAll()
    }

    suspend fun create(location: Location) = dao.add(location)

    suspend fun editName(locationName: String, id: Int) = dao.updateName(locationName, id)

    suspend fun editPhotoList(photoList: MutableList<String>, id: Int) = dao.updatePhotosList(photoList, id)

    suspend fun deleteAll() = dao.deleteAll()
}