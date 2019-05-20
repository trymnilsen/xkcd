package com.trymtrym.xkcd.api

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.trymtrym.xkcd.data.Comic
import com.trymtrym.xkcd.data.XkcdResource
import com.trymtrym.xkcd.parser.Parser;
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun getApiUrl(num: Int): String {
    return "https://xkcd.com/$num/info.0.json"
}

class XkcdApi @Inject constructor() : ComicApi {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://xkcd.com/")
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    val apiService: XkcdResource = retrofit.create(XkcdResource::class.java);

    override suspend fun getComic(num: Int): Comic? {
        val response = apiService.getComicById(num).await();
        if(response.isSuccessful) {
            return response.body();
        } else {
            Log.e("Xkcd api", "failed to make request" + response.code())
            return null;
        }
    }

    companion object {
        private const val DEFAULT_RESPONSE = ""
    }
}