package ke.co.tulivuapps.hobbyhorsetours.data.di

import androidx.compose.runtime.Stable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ke.co.tulivuapps.hobbyhorsetours.data.remote.api.PopularService
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.PopularRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.impl.PopularRemoteDataSourceImpl
import ke.co.tulivuapps.hobbyhorsetours.data.repository.PopularRepositoryImpl
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.PopularRepository
import retrofit2.Retrofit

/**
 * Created by brendenozie on 19.03.2023
 */
@Stable
@Module
@InstallIn(ViewModelComponent::class)
class PopularModule {
    @Provides
    fun provideEpisodesService(retrofit: Retrofit): PopularService =
        retrofit.create(PopularService::class.java)
    @Provides
    fun provideEpisodesRemoteDataSource(episodesService: PopularService): PopularRemoteDataSource =
        PopularRemoteDataSourceImpl(episodesService)
    @Provides
    fun provideEpisodesRepository(
        episodesRemoteDataSource: PopularRemoteDataSource
    ): PopularRepository =
        PopularRepositoryImpl(episodesRemoteDataSource)
}