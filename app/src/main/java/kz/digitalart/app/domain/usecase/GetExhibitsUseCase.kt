package kz.digitalart.app.domain.usecase

import kz.digitalart.app.data.mapper.CloudErrorMapper
import kz.digitalart.app.domain.model.Exhibit
import kz.digitalart.app.domain.repository.AppRepository
import kz.digitalart.app.domain.usecase.base.UseCase
import javax.inject.Inject

class GetExhibitsUseCase @Inject constructor(
    errorUtil: CloudErrorMapper,
    private val appRepository: AppRepository
) : UseCase<List<Exhibit>>(errorUtil) {
    override suspend fun executeOnBackground(): List<Exhibit> {
        return appRepository.getExhibits()
    }
}