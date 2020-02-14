package kz.digitalart.app.di.builder

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import kz.digitalart.app.di.module.PrefsModule
import kz.digitalart.app.ui.ViewModelFactory

@Module(includes = [PrefsModule::class, AppViewModelBuilder::class])
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}