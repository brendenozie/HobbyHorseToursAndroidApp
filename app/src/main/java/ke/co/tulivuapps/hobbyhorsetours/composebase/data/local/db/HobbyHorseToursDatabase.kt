package ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.db

import android.content.Context
import androidx.compose.runtime.Stable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.converter.EpisodeConverter
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.dao.FavoriteDao
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.FavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.Constants

/**
 * Created by brendenozie on 27.03.2023
 */
@Stable
@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(EpisodeConverter::class)
abstract class HobbyHorseToursDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var instance: HobbyHorseToursDatabase? = null

        fun getDatabase(context: Context): HobbyHorseToursDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, HobbyHorseToursDatabase::class.java, Constants.TABLE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}