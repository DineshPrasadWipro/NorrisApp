package com.ds.norris.injection

import com.ds.norris.viewmodel.category.CategoryViewModel
import com.ds.norris.viewmodel.random.RandomJokeViewModel
import com.ds.norris.viewmodel.search.SearchJokesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {

    viewModel {
        SearchJokesViewModel(get())
    }
    viewModel {
        RandomJokeViewModel(get())
    }
    viewModel {
        CategoryViewModel(get())
    }
}


