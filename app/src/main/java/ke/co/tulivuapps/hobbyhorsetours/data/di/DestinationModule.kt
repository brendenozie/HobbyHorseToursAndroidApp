package ke.co.tulivuapps.hobbyhorsetours.data.di

import androidx.compose.runtime.Stable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ke.co.tulivuapps.hobbyhorsetours.data.local.dao.DestinationDao
import ke.co.tulivuapps.hobbyhorsetours.data.remote.api.DestinationService
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.DestinationRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.impl.DestinationRemoteDataSourceImpl
import ke.co.tulivuapps.hobbyhorsetours.data.repository.DestinationRepositoryImpl
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.DestinationRepository
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