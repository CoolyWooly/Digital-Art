package kz.digitalart.app.data.source.cloud

import kz.digitalart.app.data.restful.ApiService
import kz.digitalart.app.domain.model.AboutModel
import kz.digitalart.app.domain.model.ExhibitModel
import kz.digitalart.app.domain.model.RatingModel

class CloudRepository(private val apIs: ApiService) : BaseCloudRepository {

    override suspend fun getExhibit(id: Int?, lang: String?): ExhibitModel {
        return apIs.getExhibitAsync(id, lang)
    }

    override suspend fun getExhibits(
        page: Int?,
        limit: Int?,
        search: String?,
        lang: String?,
        by_date: String?
    ): List<ExhibitModel> {
        return apIs.getExhibitsAsync(page, limit, search, lang, by_date)
    }

    override suspend fun getPopular(
        page: Int?,
        limit: Int?,
        search: String?,
        lang: String?
    ): List<ExhibitModel> {
        return apIs.getPopularAsync(page, limit, search, lang)
    }

    override suspend fun getAbout(
        lang: String?
    ): AboutModel {
        return apIs.getAboutAsync(lang)
    }

    override suspend fun setExhibitRate(
        id: Int?,
        rate: Double?
    ): RatingModel {
        return apIs.setExhibitRateAsync(id, rate)
    }
}
