package kz.digitalart.app.di.module

import dagger.Binds
import dagger.Module
import kz.digitalart.app.data.source.db.Prefs
import kz.digitalart.app.data.source.db.PrefsImpl

@Module
abstract class PrefsModule {

    @Binds
    abstract fun bindPrefsManager(prefsImpl: PrefsImpl): Prefs
}