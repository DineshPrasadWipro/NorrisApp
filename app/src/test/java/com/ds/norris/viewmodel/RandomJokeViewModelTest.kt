package com.ds.norris.viewmodel

import com.ds.norris.TestBase
import com.ds.norris.model.CustomNorris
import com.ds.norris.repository.MockNetworkRepository
import com.ds.norris.viewmodel.random.RandomJokeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RandomJokeViewModelTest() : TestBase() {

    private var mockNetworkRepository = MockNetworkRepository()

    private val viewModel = RandomJokeViewModel(mockNetworkRepository)

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    override fun setup() {
        Dispatchers.setMain(dispatcher)
        super.setup()

    }

    @After
    override fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test random joke`() {
        var expected: CustomNorris? = null
        runBlocking {
            viewModel.randomNorris.observeForever {
                if (it != null) {
                    expected = it
                }
            }
            viewModel.getRandomNorris()
        }

        Assert.assertEquals("112", expected?.id)
    }

}

