package com.trymtrym.xkcd.data

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

data class Comic (
    val month: String,
    val num: Long,
    val link: String,
    val year: String,
    val news: String,
    val safeTitle: String,
    val transcript: String,
    val alt: String,
    val img: String,
    val title: String,
    val day: String
)



//A retrofit Network Interface for the Api
interface XkcdResource{
    @GET("info.0.json")
    fun getLastComic(): Deferred<Response<Comic>>
    @GET("{id}/info.0.json")
    fun getComicById(@Path("id") id:Int): Deferred<Response<Comic>>
}