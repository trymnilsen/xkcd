package com.trymtrym.xkcd.di

import com.trymtrym.xkcd.MainActivity
import com.trymtrym.xkcd.parser.Parser
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ApiModule::class,
        ParserModule::class,
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(activity: MainActivity)
}
