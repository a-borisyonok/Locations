package by.seka.locations.ui.helper

import android.content.Context
import by.seka.locations.R
import by.seka.locations.ui.util.CATEGORY

class SharedPreferencesHelper(private val context: Context) {

    fun getCategoryName(): String {
        return context.getSharedPreferences(CATEGORY, Context.MODE_PRIVATE).getString(
            CATEGORY, context.getString(
                R.string.streets
            )
        ).toString()
    }

    fun setCategoryName(name: String) {
        context.getSharedPreferences(CATEGORY, Context.MODE_PRIVATE).edit()
            .putString(CATEGORY, name).apply()
    }
}