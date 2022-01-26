package by.seka.locations.data

import androidx.room.Database
import androidx.room.RoomDatabase
import domain.model.Location

@Database(entities = [Location::class], version = 2, exportSchema = false)
abstract class LocationsDB : RoomDatabase() {

    abstract fun participantsDao(): LocationsDao
}