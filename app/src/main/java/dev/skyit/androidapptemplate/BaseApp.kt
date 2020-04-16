package dev.skyit.androidapptemplate

import android.app.Application
import dev.skyit.androidapptemplate.dependencies.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@BaseApp)
            androidLogger(Level.DEBUG)

            modules(
                listOf(
                viewModelsModule
            ))
        }
    }
}