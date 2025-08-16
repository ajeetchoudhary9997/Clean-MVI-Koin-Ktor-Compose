package com.example.composedemo.home.domain

import com.example.composedemo.home.data.dto.RickAndMortyDto


/**
 * Created by  Ajeet Singh on Date: 09/08/25.
 */
interface HomeRepository {
    suspend fun loadCharacters(): RickAndMortyDto
}