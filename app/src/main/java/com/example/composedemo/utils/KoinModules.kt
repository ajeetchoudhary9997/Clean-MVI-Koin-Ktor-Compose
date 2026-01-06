package com.example.composedemo.utils

import com.example.composedemo.home.data.HomeRepositoryImpl
import com.example.composedemo.home.domain.HomeRepository
import com.example.composedemo.home.domain.useCases.GetUniversitiesUseCase
import com.example.composedemo.home.presentation.viewModel.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Created by  Ajeet Singh on Date: 09/08/25.
 */
val appModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true  // Ignore fields not in your DTO
                    isLenient = true          // Allow non-strict JSON
                })
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 15000
                socketTimeoutMillis = 15000
            }
            install(DefaultRequest) {
                url {
                    host = HttpRoutes.BASE_URL
                    protocol = URLProtocol.HTTPS
                }
            }

        }
    }
    singleOf(::HomeRepositoryImpl) { bind<HomeRepository>() }
    singleOf(::GetUniversitiesUseCase)
    viewModelOf(::HomeViewModel)
}