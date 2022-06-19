package com.ds.norris

import android.app.Application
import com.ds.norris.injection.appModule
import com.ds.norris.injection.repositoryModule
import com.ds.norris.injection.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NorrisApplication : Application() {

    private val appModules = listOf(
        repositoryModule,
        appModule,
        vmModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NorrisApplication)
            modules(appModules)
        }
    }
}