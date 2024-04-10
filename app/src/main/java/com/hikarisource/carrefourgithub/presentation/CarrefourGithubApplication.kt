package com.hikarisource.carrefourgithub.presentation

import android.app.Application
import com.hikarisource.carrefourgithub.core.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class CarrefourGithubApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() = org.koin.core.context.startKoin {
        androidContext(this@CarrefourGithubApplication)
        modules(AppModules.modules)
        androidLogger()
    }
}