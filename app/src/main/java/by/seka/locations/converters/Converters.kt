package by.seka.locations.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun fromString(stringListString: String?): MutableList<String> {
        return stringListString?.split(",")?.map { it } as MutableList<String>
    }

    @TypeConverter
    fun toString(stringList: MutableList<String?>?): String {
        return stringList?.joinToString(separator = ",") ?: ""
    }
}