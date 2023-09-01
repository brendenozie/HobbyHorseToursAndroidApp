package ke.co.tulivuapps.hobbyhorsetours.composebase.data.di

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.api.EpisodesService
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.EpisodesRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.source.impl.EpisodesRemoteDataSourceImpl
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.repository.EpisodesRepositoryImpl
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.EpisodesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

/**
 * Created by brendenozie on 19.03.2023
 */
@Stable
@Module
@InstallIn(ViewModelComponent::class)
class EpisodesModule {
    @Provides
    fun provideEpisodesService(retrofit: Retrofit): EpisodesService =
        retrofit.create(EpisodesService::class.java)

    @Provides
    fun provideEpisodesRemoteDataSource(episodesService: EpisodesService): EpisodesRemoteDataSource =
        EpisodesRemoteDataSourceImpl(episodesService)

    @Provides
    fun provideEpisodesRepository(
        episodesRemoteDataSource: EpisodesRemoteDataSource
    ): EpisodesRepository =
        EpisodesRepositoryImpl(episodesRemoteDataSource)
}