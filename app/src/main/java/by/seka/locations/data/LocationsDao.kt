package by.seka.locations.data

import androidx.room.*
import by.seka.locations.domain.model.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationsDao {

    @Query("SELECT * FROM locations")
    fun getAll(): Flow<List<Location>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(location: Location)

//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun update(location: Location)

    @Query("UPDATE locations SET locationName=:locationName WHERE id = :id")
    suspend fun updateName(locationName: String, id: Int)

    @Query("UPDATE locations SET photosList=:photosList WHERE id = :id")
    suspend fun updatePhotosList(photosList: MutableList<String>, id: Int)


    @Query("UPDATE locations SET photosList=null WHERE id = :id")
    suspend fun removeAllPhotos(id: Int)

    @Delete
    suspend fun delete(location: Location)

    @Query("DELETE FROM locations")
    suspend fun deleteAll()
}