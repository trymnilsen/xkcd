package com.trymtrym.xkcd.di

import dagger.Binds
import dagger.Module
import com.trymtrym.xkcd.parser.JsonParser;
import com.trymtrym.xkcd.parser.Parser;
import javax.inject.Singleton

@Module
interface ParserModule {
    @Binds @Singleton fun provideParser(parser: JsonParser): Parser<String, String>
}
