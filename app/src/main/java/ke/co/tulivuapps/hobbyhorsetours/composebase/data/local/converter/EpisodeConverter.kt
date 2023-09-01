package ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.converter

import androidx.compose.runtime.Stable
import androidx.room.TypeConverter
import ke.co.tulivuapps.hobbyhorsetours.composebase.utils.Utility.fromJson
import ke.co.tulivuapps.hobbyhorsetours.composebase.utils.Utility.toJson

/**
 * Created by brendenozie on 27.03.2023
 */

@Stable
class EpisodeConverter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.fromJson()
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString.toJson()
    }
}

