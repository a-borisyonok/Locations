package by.seka.locations.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import by.seka.locations.data.converters.Converters

@Entity(tableName = "locations")
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val locationName: String,
    @TypeConverters(Converters::class)
    val photosList: MutableList<String>? = null
)
