package com.trymtrym.xkcd.api

import com.trymtrym.xkcd.data.Comic

interface ComicApi {
    suspend fun getComic(num: Int): Comic?
}