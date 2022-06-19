package com.ds.norris.injection

import com.ds.norris.repository.INetworkRepository
import com.ds.norris.repository.NetworkRepository
import org.koin.dsl.module


val repositoryModule = module {
    single<INetworkRepository> {
        NetworkRepository(get())
    }
}
