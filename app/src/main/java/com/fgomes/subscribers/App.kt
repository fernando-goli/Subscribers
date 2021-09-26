package com.fgomes.subscribers

import android.app.Application
import com.fgomes.subscribers.di.repositoryModule
import com.fgomes.subscribers.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(viewModelModule)
            modules(repositoryModule)
        }
    }
}