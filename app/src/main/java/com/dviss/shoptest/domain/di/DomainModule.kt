package com.dviss.shoptest.domain.di

import com.dviss.shoptest.domain.repository.ProductRepository
import com.dviss.shoptest.domain.usecase.AppUseCases
import com.dviss.shoptest.domain.usecase.DownloadProducts
import com.dviss.shoptest.domain.usecase.GetProducts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @ViewModelScoped
    @Provides
    fun provideUseCases(
        repository: ProductRepository
    ): AppUseCases {
        return AppUseCases(
            getProducts = GetProducts(repository),
            downloadProducts = DownloadProducts(repository)
        )
    }
}