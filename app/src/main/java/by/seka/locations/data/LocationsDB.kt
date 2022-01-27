package by.seka.locations.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.seka.locations.converters.Converters
import by.seka.locations.domain.model.Location

@Database(entities = [Location::class], version = 4, exportSchema = false)

@TypeConverters(Converters::class)

abstract class LocationsDB : RoomDatabase() {

    abstract fun participantsDao(): LocationsDao
}