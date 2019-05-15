package com.trymtrym.xkcd

import android.app.Application
import com.trymtrym.xkcd.di.AppModule
import com.trymtrym.xkcd.di.NetworkModule
import com.trymtrym.xkcd.di.AppComponent
import com.trymtrym.xkcd.di.DaggerAppComponent

class App : Application() {
    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .networkModule(NetworkModule())
            .appModule(AppModule(this))
            .build()
    }

    fun component(): AppComponent = component
}
