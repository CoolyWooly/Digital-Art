package kz.digitalart.app.data.source.cloud

import kz.digitalart.app.domain.model.Exhibit

interface BaseCloudRepository {
    suspend fun getExhibits(): List<Exhibit>
}