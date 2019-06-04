package kz.digitalart.app.data.repository

import kz.digitalart.app.data.source.cloud.BaseCloudRepository
import kz.digitalart.app.domain.model.Exhibit
import kz.digitalart.app.domain.repository.AppRepository
import javax.inject.Inject

class AppRepoImp @Inject constructor(
    private val cloudRepository: BaseCloudRepository
) : AppRepository {

    override suspend fun getExhibits(): List<Exhibit> {
        return arrayListOf()
    }
}