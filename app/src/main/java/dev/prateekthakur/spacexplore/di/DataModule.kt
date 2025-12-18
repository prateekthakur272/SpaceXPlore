package dev.prateekthakur.spacexplore.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.prateekthakur.spacexplore.data.api.SpaceXApi
import dev.prateekthakur.spacexplore.data.repository.CapsuleRepositoryImpl
import dev.prateekthakur.spacexplore.domain.repository.CapsuleRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides @Singleton
    fun provideCapsuleRepository(spaceXApi: SpaceXApi) : CapsuleRepository {
        return CapsuleRepositoryImpl(spaceXApi)
    }
}