package com.example.composedemo.home.data

import com.example.composedemo.home.data.dto.RickAndMortyDto
import com.example.composedemo.home.domain.HomeRepository
import com.example.composedemo.utils.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

/**
 * Created by  Ajeet Singh on Date: 09/08/25.
 */
class HomeRepositoryImpl(val httpClient: HttpClient): HomeRepository {
    override suspend fun loadCharacters(): RickAndMortyDto {
        return httpClient.get(HttpRoutes.CHARACTER,).body()
    }
}