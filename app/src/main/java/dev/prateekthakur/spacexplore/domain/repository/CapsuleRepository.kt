package dev.prateekthakur.spacexplore.domain.repository

import dev.prateekthakur.spacexplore.domain.models.Capsule

interface CapsuleRepository {
    suspend fun getAll() : List<Capsule>
    suspend fun getSingle(id: String) : Capsule
    suspend fun query(serial: String, status: String, type: String) : List<Capsule>
}
