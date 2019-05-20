package com.trymtrym.xkcd

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trymtrym.xkcd.api.ComicApi
import com.trymtrym.xkcd.api.XkcdApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class ComicViewModel(private val api: ComicApi) : ViewModel() {
    private val data = MutableLiveData<String>()
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    fun data(): LiveData<String> = data

    fun requestNewComic() {
        val randomComicNumber = Random.nextInt(2149)
        Log.i("comic","Requesting comic number $randomComicNumber")
        scope.launch(Dispatchers.IO) {
            val joke = api.getComic(randomComicNumber)
            if (joke.isNotEmpty()) {
                data.postValue(joke)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    class Factory @Inject constructor(
        private val api: ComicApi
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = ComicViewModel(api) as T
    }
}
