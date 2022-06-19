package com.ds.norris.viewmodel

import com.ds.norris.TestBase
import com.ds.norris.model.CustomNorrisList
import com.ds.norris.repository.MockNetworkRepository
import com.ds.norris.viewmodel.search.SearchJokesViewModel
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
class SearchJokeViewModelTest : TestBase() {

    private var mockNetworkRepository = MockNetworkRepository()

    private val viewModel = SearchJokesViewModel(mockNetworkRepository)

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
        var actual = CustomNorrisList("", emptyList())
        var expected = CustomNorrisList("", emptyList())

        viewModel.norrisList.observeForever {
            if (it != null) {
                expected = it
            }
        }
        Assert.assertEquals(actual.result, expected.result)
        runBlocking {
            viewModel.searchNorris("angel")
        }
        Assert.assertNotEquals(actual.result, expected.result)
    }

}

