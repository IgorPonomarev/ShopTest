package com.dviss.shoptest.data.di

import android.app.Application
import androidx.room.Room
import com.dviss.shoptest.data.local.ProductDatabase
import com.dviss.shoptest.data.remote.ProductServiceImpl
import com.dviss.shoptest.data.repository.ProductRepositoryImpl
import com.dviss.shoptest.domain.remote.ProductService
import com.dviss.shoptest.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    //=========================== Remote ==================================
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return HttpClient(Android) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
            }
        }
    }

    @Provides
    @Singleton
    fun provideProductService(client: HttpClient): ProductService {
        return ProductServiceImpl(client)
    }

    //=========================== Local ==================================

    @Provides
    @Singleton
    fun provideCurrencyDatabase(app: Application): ProductDatabase {
        return Room.databaseBuilder(
            app,
            ProductDatabase::class.java,
            "currency_db"
        ).build()
    }

    //=========================== Repository ==================================

    @Provides
    @Singleton
    fun provideProductRepository(
        productDatabase: ProductDatabase,
        productService: ProductService
    ): ProductRepository {
        return ProductRepositoryImpl(productDatabase.dao, productService)
    }


}