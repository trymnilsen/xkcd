package com.trymtrym.xkcd.di

import com.trymtrym.xkcd.api.ComicApi
import com.trymtrym.xkcd.api.XkcdApi
import dagger.Binds
import dagger.Module

@Module
interface ApiModule {
    @Binds fun providesXkcdApi(api: XkcdApi): ComicApi
}
