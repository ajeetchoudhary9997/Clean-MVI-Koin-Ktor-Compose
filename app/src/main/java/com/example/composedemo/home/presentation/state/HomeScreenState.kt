package com.example.composedemo.home.presentation.state

import com.example.composedemo.home.domain.model.Character

/**
 * Created by  Ajeet Singh on Date: 09/08/25.
 */
data class HomeScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: String="",
    val characters: List<Character> = emptyList()
)

