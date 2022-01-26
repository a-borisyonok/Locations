package domain

import android.util.Log
import by.seka.locations.data.LocationsDao
import domain.model.Location
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class Repository @Inject constructor (private val dao: LocationsDao) {

    fun getAll(): Flow<List<Location>> {
Log.i("repository", dao.getAll().toString())

       return dao.getAll()
    }

    suspend fun create(location: Location) = dao.add(location)

    suspend fun edit(locationName: String, id: Int) = dao.update(locationName, id)

    suspend fun deleteAll() = dao.deleteAll()
}