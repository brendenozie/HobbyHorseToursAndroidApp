package ke.co.tulivuapps.hobbyhorsetours.composebase.data.di

import android.content.Context
import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.composebase.HobbyHorseToursApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by brendenozie on 01.09.2023
 */

@Stable
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): HobbyHorseToursApp {
        return app as HobbyHorseToursApp
    }
}