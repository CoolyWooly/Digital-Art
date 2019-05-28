package kz.digitalart.app.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kz.digitalart.app.ui.MainActivity
import kz.digitalart.app.ui.welcome.WelcomeActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityProviders::class])
    abstract fun bindWelcomeActivity(): WelcomeActivity

    @ContributesAndroidInjector(modules = [MainActivityProviders::class])
    abstract fun bindMainActivity(): MainActivity

}