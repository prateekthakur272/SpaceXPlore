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
import dev.prateekthakur.spacexplore.data.repository.DragonRepositoryImpl
import dev.prateekthakur.spacexplore.data.repository.EventHistoryRepositoryImpl
import dev.prateekthakur.spacexplore.data.repository.LandingPadsRepositoryImpl
import dev.prateekthakur.spacexplore.data.repository.LaunchesRepositoryImpl
import dev.prateekthakur.spacexplore.domain.repository.AboutSpaceXRepository
import dev.prateekthakur.spacexplore.domain.repository.CapsuleRepository
import dev.prateekthakur.spacexplore.domain.repository.CoreRepository
import dev.prateekthakur.spacexplore.domain.repository.CrewRepository
import dev.prateekthakur.spacexplore.domain.repository.DragonRepository
import dev.prateekthakur.spacexplore.domain.repository.EventHistoryRepository
import dev.prateekthakur.spacexplore.domain.repository.LandingPadsRepository
import dev.prateekthakur.spacexplore.domain.repository.LaunchesRepository
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

    @Provides @Singleton
    fun provideDragonRepository(spaceXApi: SpaceXApi) : DragonRepository {
        return DragonRepositoryImpl(spaceXApi)
    }

    @Provides @Singleton
    fun provideEventHistoryRepository(spaceXApi: SpaceXApi) : EventHistoryRepository {
        return EventHistoryRepositoryImpl(spaceXApi)
    }

    @Provides @Singleton
    fun provideLandingPadsRepository(spaceXApi: SpaceXApi) : LandingPadsRepository {
        return LandingPadsRepositoryImpl(spaceXApi)
    }

    @Provides @Singleton
    fun provideLaunchesRepository(spaceXApi: SpaceXApi) : LaunchesRepository {
        return LaunchesRepositoryImpl(spaceXApi)
    }
}