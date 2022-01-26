package by.seka.locations.data

import androidx.room.*
import domain.model.Location
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
   suspend fun update(locationName: String, id: Int)

    @Delete
    suspend fun delete(location: Location)

    @Query("DELETE FROM locations")
    suspend fun deleteAll()
}