package com.trymtrym.xkcd.api

import com.trymtrym.xkcd.parser.Parser;
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

fun getApiUrl(num: Int): String {
    return "https://xkcd.com/$num/info.0.json"
}

class XkcdApi @Inject constructor(
    private val client: OkHttpClient,
    private val parser: Parser<String, String>
) : ComicApi {
    override suspend fun getComic(num: Int): String {
        var comicUrl = getApiUrl(num);
        print("Comic url is: $comicUrl")
        val request = Request.Builder()
            .url(comicUrl)
            .build()
        val response = client.newCall(request).execute()
        return if (response.isSuccessful) {
            val body = response.body() ?: return DEFAULT_RESPONSE
            parser.parse(body.string())
        } else {
            DEFAULT_RESPONSE
        }
    }

    companion object {
        private const val DEFAULT_RESPONSE = ""
    }
}