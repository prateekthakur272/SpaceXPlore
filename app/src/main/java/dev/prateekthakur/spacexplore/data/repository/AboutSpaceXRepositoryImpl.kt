package dev.prateekthakur.spacexplore.data.repository

import dev.prateekthakur.spacexplore.data.api.SpaceXApi
import dev.prateekthakur.spacexplore.domain.models.AboutSpaceX
import dev.prateekthakur.spacexplore.domain.repository.AboutSpaceXRepository
import javax.inject.Inject

class AboutSpaceXRepositoryImpl @Inject constructor(
    private val spaceXApi: SpaceXApi
) : AboutSpaceXRepository {

    override suspend fun getInfo(): AboutSpaceX {
        return spaceXApi.getCompanyInfo()
    }

}