package ke.co.tulivuapps.hobbyhorsetours.data.local.converter

import androidx.compose.runtime.Stable
import androidx.room.TypeConverter
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.fromJson
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.toJson

/**
 * Created by brendenozie on 27.03.2023
 */

@Stable
class PopularConverter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.fromJson(stringValue)
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString.toJson()
    }
}

