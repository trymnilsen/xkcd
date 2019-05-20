package com.trymtrym.xkcd

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trymtrym.xkcd.api.ComicApi
import com.trymtrym.xkcd.api.XkcdApi
import com.trymtrym.xkcd.data.Comic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class ComicViewModel(private val api: ComicApi) : ViewModel() {
    private val data = MutableLiveData<List<Comic>>()
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    fun data(): LiveData<List<Comic>> = data

    fun requestNewComic() {
        scope.launch(Dispatchers.IO) {
            val list: MutableList<Comic> = mutableListOf()
            repeat(20) {
                val comic = api.getComic(Random.nextInt(2149))
                if(comic != null) {
                    list.add(comic)
                }
            }
            data.postValue(list);
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
