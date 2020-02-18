package kz.digitalart.app.core

import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kz.digitalart.app.di.component.DaggerCoreComponent

class App : DaggerApplication() {

    companion object {
        lateinit var instance: App private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerCoreComponent
            .builder()
            .application(this)
            .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}