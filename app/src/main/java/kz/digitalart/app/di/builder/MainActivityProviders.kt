package kz.digitalart.app.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kz.digitalart.app.ui.about.AboutFragment
import kz.digitalart.app.ui.home.HomeFragment
import kz.digitalart.app.ui.home.details.HomeDetailsFragment
import kz.digitalart.app.ui.liked.LikedFragment
import kz.digitalart.app.ui.liked.details.LikedDetailsFragment
import kz.digitalart.app.ui.qr.QrFragment
import kz.digitalart.app.ui.settings.SettingsFragment

@Module
abstract class MainActivityProviders {
    @ContributesAndroidInjector
    abstract fun provideQrFragment(): QrFragment

    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun provideHomeDetailsFragment(): HomeDetailsFragment

    @ContributesAndroidInjector
    abstract fun provideSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun provideAboutFragment(): AboutFragment

    @ContributesAndroidInjector
    abstract fun provideLikedFragment(): LikedFragment

    @ContributesAndroidInjector
    abstract fun provideLikedDetailsFragment(): LikedDetailsFragment

}