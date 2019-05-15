package com.trymtrym.xkcd.api

interface ComicApi {
    suspend fun getComic(num: Int): String
}