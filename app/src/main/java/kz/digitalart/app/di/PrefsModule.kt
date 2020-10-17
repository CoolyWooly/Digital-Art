package kz.digitalart.app.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.digitalart.app.data.db.Prefs
import kz.digitalart.app.data.db.PrefsImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class PrefsModule {

    @Binds
    abstract fun bindPrefsManager(prefsImpl: PrefsImpl): Prefs
}