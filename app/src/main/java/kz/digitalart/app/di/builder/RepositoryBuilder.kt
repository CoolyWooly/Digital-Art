package kz.digitalart.app.di.builder

import dagger.Binds
import dagger.Module
import kz.digitalart.app.data.repository.AppRepoImp
import kz.digitalart.app.domain.repository.AppRepository

@Module
abstract class RepositoryBuilder {
    @Binds
    abstract fun bindsMovieRepository(repoImp: AppRepoImp): AppRepository
}