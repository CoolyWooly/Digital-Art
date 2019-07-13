package kz.digitalart.app.data.source.cloud

import kz.digitalart.app.data.restful.ApiService
import kz.digitalart.app.domain.model.About
import kz.digitalart.app.domain.model.Exhibit
import kz.digitalart.app.domain.model.Rating

class CloudRepository(private val apIs: ApiService) : BaseCloudRepository {
    override suspend fun getExhibit(id: Int?, lang: String?): Exhibit {
        return apIs.getExhibitAsync(id, lang)
    }

    override suspend fun getExhibits(
        page: Int?,
        limit: Int?,
        search: String?,
        lang: String?,
        by_date: String?
    ): List<Exhibit> {
        return apIs.getExhibitsAsync(page, limit, search, lang, by_date)
    }

    override suspend fun getPopular(
        page: Int?,
        limit: Int?,
        search: String?,
        lang: String?
    ): List<Exhibit> {
        return apIs.getPopularAsync(page, limit, search, lang)
    }

    override suspend fun getAbout(
        lang: String?
    ): About {
        return apIs.getAboutAsync(lang)
    }

    override suspend fun setExhibitRate(
        id: Int?,
        rate: Double?
    ): Rating {
        return apIs.setExhibitRateAsync(id, rate)
    }
}
