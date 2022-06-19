package com.ds.norris.viewmodel

import com.ds.norris.TestBase
import com.ds.norris.repository.MockNetworkRepository
import com.ds.norris.viewmodel.category.CategoryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoryViewModelTest : TestBase() {

    private var mockNetworkRepository = MockNetworkRepository()

    private val viewModel = CategoryViewModel(mockNetworkRepository)


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
    fun `getCategories`() {
        var expected: List<String> = emptyList()
        runBlocking {
            viewModel.categoryList.observeForever {
                if (it != null) {
                    expected = it
                }
            }
            viewModel.getCategories()
        }

        assertEquals(setCategories(), expected)
    }

    private fun setCategories(): List<String> {
        return listOf("animal")

    }
}