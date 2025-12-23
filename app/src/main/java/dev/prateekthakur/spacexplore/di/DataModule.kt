package dev.prateekthakur.spacexplore.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.prateekthakur.spacexplore.data.api.SpaceXApi
import dev.prateekthakur.spacexplore.data.repository.AboutSpaceXRepositoryImpl
import dev.prateekthakur.spacexplore.data.repository.CapsuleRepositoryImpl
import dev.prateekthakur.spacexplore.data.repository.CoreRepositoryImpl
import dev.prateekthakur.spacexplore.data.repository.CrewRepositoryImpl
import dev.prateekthakur.spacexplore.domain.repository.AboutSpaceXRepository
import dev.prateekthakur.spacexplore.domain.repository.CapsuleRepository
import dev.prateekthakur.spacexplore.domain.repository.CoreRepository
import dev.prateekthakur.spacexplore.domain.repository.CrewRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides @Singleton
    fun provideCapsuleRepository(spaceXApi: SpaceXApi) : CapsuleRepository {
        return CapsuleRepositoryImpl(spaceXApi)
    }

    @Provides @Singleton
    fun provideAboutSpaceXRepository(spaceXApi: SpaceXApi) : AboutSpaceXRepository {
        return AboutSpaceXRepositoryImpl(spaceXApi)
    }

    @Provides @Singleton
    fun provideCoreRepository(spaceXApi: SpaceXApi) : CoreRepository {
        return CoreRepositoryImpl(spaceXApi)
    }

    @Provides @Singleton
    fun provideCrewRepository(spaceXApi: SpaceXApi) : CrewRepository{
        return CrewRepositoryImpl(spaceXApi)
    }
}