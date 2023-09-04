package ke.co.tulivuapps.hobbyhorsetours.composebase.data.di

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.dao.FavoriteDao
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.api.DestinationService
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.DestinationRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.impl.DestinationRemoteDataSourceImpl
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.repository.DestinationRepositoryImpl
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.DestinationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.local.dao.DestinationDao
import retrofit2.Retrofit

/**
 * Created by brendenozie on 12.03.2023
 */
@Stable
@Module
@InstallIn(ViewModelComponent::class)
class DestinationModule {
    @Provides
    fun provideDestinationService(retrofit: Retrofit): DestinationService =
        retrofit.create(DestinationService::class.java)

    @Provides
    fun provideDestinationRemoteDataSource(
        characterService: DestinationService,
        dao: DestinationDao
    ): DestinationRemoteDataSource =
        DestinationRemoteDataSourceImpl(characterService, dao)

    @Provides
    fun provideDestinationRepository(
        accountRemoteDataSource: DestinationRemoteDataSource,
        dao: DestinationDao
    ): DestinationRepository =
        DestinationRepositoryImpl(accountRemoteDataSource, dao)
}