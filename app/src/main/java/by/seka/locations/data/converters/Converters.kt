package by.seka.locations.data.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import by.seka.locations.ui.util.EMPTY_STRING

@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun fromString(stringListString: String?): MutableList<String> {
        return stringListString?.split(",")?.map { it } as? MutableList<String> ?: mutableListOf(
            EMPTY_STRING
        )
    }

    @TypeConverter
    fun toString(stringList: MutableList<String?>?): String {
        return stringList?.joinToString(separator = ",") ?: EMPTY_STRING


    }
}