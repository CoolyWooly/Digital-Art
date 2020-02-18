package kz.digitalart.app.data.cloud.repository

import kz.digitalart.app.data.cloud.ResultWrapper
import kz.digitalart.app.domain.model.AboutModel
import kz.digitalart.app.domain.model.ExhibitModel
import kz.digitalart.app.domain.model.RatingModel

interface BaseCloudRepository {

    suspend fun getExhibit(id: Int?, lang: String?): ResultWrapper<ExhibitModel>

    suspend fun getExhibits(page: Int?, limit: Int?, search: String?, lang: String?, by_date: String?): ResultWrapper<List<ExhibitModel>>

    suspend fun getPopular(page: Int?, limit: Int?, search: String?, lang: String?): ResultWrapper<List<ExhibitModel>>

    suspend fun getAbout(lang: String?): ResultWrapper<AboutModel>

    suspend fun setExhibitRate(id: Int?, rate: Double?): ResultWrapper<RatingModel>
}