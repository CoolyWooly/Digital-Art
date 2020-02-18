package kz.digitalart.app.data.cloud.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kz.digitalart.app.data.cloud.ResultWrapper
import kz.digitalart.app.data.cloud.rest.ApiService
import kz.digitalart.app.data.cloud.safeApiCall
import kz.digitalart.app.domain.model.AboutModel
import kz.digitalart.app.domain.model.ExhibitModel
import kz.digitalart.app.domain.model.RatingModel

class CloudRepository(
    private val apIs: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseCloudRepository {

    override suspend fun getExhibit(
        id: Int?,
        lang: String?
    ): ResultWrapper<ExhibitModel> {
        return safeApiCall(dispatcher) {
            apIs.getExhibitAsync(id, lang)
        }
    }

    override suspend fun getExhibits(
        page: Int?,
        limit: Int?,
        search: String?,
        lang: String?,
        by_date: String?
    ): ResultWrapper<List<ExhibitModel>> {
        return safeApiCall(dispatcher) {
            apIs.getExhibitsAsync(page, limit, search, lang, by_date)
        }
    }

    override suspend fun getPopular(
        page: Int?,
        limit: Int?,
        search: String?,
        lang: String?
    ): ResultWrapper<List<ExhibitModel>> {
        return safeApiCall(dispatcher) {
            apIs.getPopularAsync(page, limit, search, lang)
        }
    }

    override suspend fun getAbout(
        lang: String?
    ): ResultWrapper<AboutModel> {
        return safeApiCall(dispatcher) {
            apIs.getAboutAsync(lang)
        }
    }

    override suspend fun setExhibitRate(
        id: Int?,
        rate: Double?
    ): ResultWrapper<RatingModel> {
        return safeApiCall(dispatcher) {
            apIs.setExhibitRateAsync(id, rate)
        }
    }
}
