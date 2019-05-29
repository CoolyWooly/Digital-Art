package kz.digitalart.app.di.builder

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kz.digitalart.app.di.qualifier.ViewModelKey
import kz.digitalart.app.ui.about.AboutViewModel
import kz.digitalart.app.ui.home.HomeViewModel
import kz.digitalart.app.ui.home.details.HomeDetailsViewModel
import kz.digitalart.app.ui.liked.LikedViewModel
import kz.digitalart.app.ui.liked.details.LikedDetailsViewModel
import kz.digitalart.app.ui.qr.QrViewModel
import kz.digitalart.app.ui.settings.SettingsViewModel
import kz.digitalart.app.ui.welcome.WelcomeViewModel

@Module
abstract class AppViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(QrViewModel::class)
    abstract fun bindQrViewModel(qrViewModel: QrViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeDetailsViewModel::class)
    abstract fun bindHomeDetailsViewModel(homeDetailsViewModel: HomeDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel::class)
    abstract fun bindAboutViewModel(aboutViewModel: AboutViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WelcomeViewModel::class)
    abstract fun bindWelcomeViewModel(welcomeViewModel: WelcomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LikedViewModel::class)
    abstract fun bindLikedViewModel(likedViewModel: LikedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LikedDetailsViewModel::class)
    abstract fun bindLikedDetailsViewModel(likedDetailsViewModel: LikedDetailsViewModel): ViewModel
}