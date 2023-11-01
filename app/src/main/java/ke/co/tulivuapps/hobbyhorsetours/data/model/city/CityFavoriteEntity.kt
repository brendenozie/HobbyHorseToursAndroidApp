package ke.co.tulivuapps.hobbyhorsetours.data.model.city

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.Constants


/**
 * Created by brendenozie on 27.03.2023
 */


@Entity(tableName = Constants.CITY_TABLE_NAME)
data class CityFavoriteEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = Constants.COLUMN_LOCAL_ID)  val localId: Int,
    @ColumnInfo(name = Constants.COLUMN_ID)  val id: String?,
    @ColumnInfo(name = Constants.COLUMN_CITY_NAME)  val cityName : String,
    @ColumnInfo(name = Constants.COLUMN_PUBLIC_ID)  val  publicId : String,
    @ColumnInfo(name = Constants.COLUMN_URL)  val  url   :   String,
    @ColumnInfo(name = Constants.COLUMN_STATUS)  val  status  : String
)