package ke.co.tulivuapps.hobbyhorsetours.composebase.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.Constants


/**
 * Created by brendenozie on 27.03.2023
 */


@Entity(tableName = Constants.TRAVEL_STYLE_TABLE_NAME)
data class TravelStyleFavoriteEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = Constants.COLUMN_LOCAL_ID)  val localId: Int,
    @ColumnInfo(name = Constants.COLUMN_ID)  val id: String?,
    @ColumnInfo(name = Constants.COLUMN_STYLE_NAME)  val styleName : String,
    @ColumnInfo(name = Constants.COLUMN_PUBLIC_ID)  val publicId : String,
    @ColumnInfo(name = Constants.COLUMN_URL)  val url      : String,
    @ColumnInfo(name = Constants.COLUMN_STATUS)  val status   : String,
    @ColumnInfo(name = Constants.COLUMN_IMG)  val img: String,
    @ColumnInfo(name = Constants.COLUMN_CREATED_AT) val createdAt: String?
)