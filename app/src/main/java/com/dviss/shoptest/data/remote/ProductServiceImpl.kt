package com.dviss.shoptest.data.remote

import android.util.Log
import com.dviss.shoptest.data.remote.dto.ProductDto
import com.dviss.shoptest.data.remote.mapper.toProduct
import com.dviss.shoptest.domain.model.Product
import com.dviss.shoptest.domain.remote.ProductService
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url

private const val TAG = "ProductServiceImpl"

class ProductServiceImpl(
    private val client:HttpClient
): ProductService {
    override suspend fun downloadProducts(): List<Product> {
        val response: List<ProductDto> = try {
            client.get {
                url(HttpRoutes.PRODUCTS_URL)
            }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Log.d(TAG, "downloadProducts: ${e.response.status.description}")
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Log.d(TAG, "downloadProducts: ${e.response.status.description}")
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Log.d(TAG, "downloadProducts: ${e.response.status.description}")
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: NoTransformationFoundException) {
            //Could not parse for some reason
            Log.d(TAG, "downloadProducts: ${e.message}")
            emptyList()
        } catch (e: Exception) {
            // other
            Log.d(TAG, "getCurrenciesFromCBR: ${e.message}")
            println("Error: ${e.message}")
            emptyList()
        }

        return response.map { it.toProduct() }
    }
}