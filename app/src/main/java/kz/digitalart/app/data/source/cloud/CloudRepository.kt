package kz.digitalart.app.data.source.cloud

import kz.digitalart.app.data.restful.ApiService
import kz.digitalart.app.domain.model.Exhibit

class CloudRepository(private val apIs: ApiService) : BaseCloudRepository {
    override suspend fun getExhibits(): List<Exhibit> {
        return apIs.getExhibitsAsync().await()
    }
}
