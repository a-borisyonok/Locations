package by.seka.locations.di

import android.content.Context
import androidx.room.Room
import by.seka.locations.data.converters.Converters
import by.seka.locations.data.LocationsDB
import by.seka.locations.data.LocationsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): LocationsDB {

        return Room.databaseBuilder(
            appContext,
            LocationsDB::class.java,
            "locations.db"
        )
            .fallbackToDestructiveMigration()
            .addTypeConverter(Converters())
            .build()
    }

    @Provides
    fun provideLocationsDAO(database: LocationsDB): LocationsDao =
        database.participantsDao()
}