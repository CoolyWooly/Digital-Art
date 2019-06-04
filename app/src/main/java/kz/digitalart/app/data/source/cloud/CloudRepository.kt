package kz.digitalart.app.data.source.cloud

import kz.digitalart.app.data.restful.ApiService
import kz.digitalart.app.domain.model.About
import kz.digitalart.app.domain.model.Exhibit

class CloudRepository(private val apIs: ApiService) : BaseCloudRepository {
    override suspend fun getExhibit(id: Int?, lang: String?): Exhibit {
        return apIs.getExhibitAsync(id, lang).await()
    }

    override suspend fun getExhibits(page: Int?, limit: Int?, search: String?, lang: String?): List<Exhibit> {
        return apIs.getExhibitsAsync(page, limit, search, lang).await()
    }

    override suspend fun getPopular(page: Int?, limit: Int?, search: String?, lang: String?): List<Exhibit> {
        return apIs.getPopularAsync(page, limit, search, lang).await()
    }

    override suspend fun getAbout(): About {
        return apIs.getAboutAsync().await()
    }
}
