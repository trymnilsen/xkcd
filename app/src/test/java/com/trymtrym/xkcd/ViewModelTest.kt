package com.trymtrym.xkcd

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.trymtrym.xkcd.api.ComicApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class ViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun testGetNewComic() {
        val api = mockk<ComicApi>()
        coEvery { api.getComic(any()) } returns "hello"
        val viewModel = ComicViewModel(api)
        runBlocking {
            viewModel.requestNewComic()
        }
        coVerify { api.getComic(any()) }
        expectThat(viewModel.data().value).isEqualTo("hello")
    }
}