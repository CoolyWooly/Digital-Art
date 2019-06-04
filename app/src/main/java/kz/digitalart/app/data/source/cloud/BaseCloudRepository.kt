package kz.digitalart.app.data.source.cloud

import kz.digitalart.app.domain.model.About
import kz.digitalart.app.domain.model.Exhibit

interface BaseCloudRepository {
    suspend fun getExhibit(id: Int?, lang: String?): Exhibit

    suspend fun getExhibits(page: Int?, limit: Int?, search: String?, lang: String?): List<Exhibit>

    suspend fun getPopular(page: Int?, limit: Int?, search: String?, lang: String?): List<Exhibit>

    suspend fun getAbout(): About
}