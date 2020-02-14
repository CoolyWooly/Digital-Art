package kz.digitalart.app.data.source.cloud

import kz.digitalart.app.domain.model.AboutModel
import kz.digitalart.app.domain.model.ExhibitModel
import kz.digitalart.app.domain.model.RatingModel

interface BaseCloudRepository {

    suspend fun getExhibit(id: Int?, lang: String?): ExhibitModel

    suspend fun getExhibits(page: Int?, limit: Int?, search: String?, lang: String?, by_date: String?): List<ExhibitModel>

    suspend fun getPopular(page: Int?, limit: Int?, search: String?, lang: String?): List<ExhibitModel>

    suspend fun getAbout(lang: String?): AboutModel

    suspend fun setExhibitRate(id: Int?, rate: Double?): RatingModel
}