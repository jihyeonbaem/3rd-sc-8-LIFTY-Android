package page.lifty.gdsclifty.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import page.lifty.gdsclifty.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}