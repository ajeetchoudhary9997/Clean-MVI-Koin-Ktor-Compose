package com.example.composedemo.home.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composedemo.home.domain.useCases.GetUniversitiesUseCase
import com.example.composedemo.home.presentation.actions.HomeScreenActions
import com.example.composedemo.home.presentation.state.HomeScreenState
import com.example.composedemo.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by  Ajeet Singh on Date: 09/08/25.
 */
class HomeViewModel(private val getUniversitiesUseCase: GetUniversitiesUseCase) : ViewModel() {
    var homeState by mutableStateOf(HomeScreenState())
        private set
    fun handleActions(actions: HomeScreenActions) {
        when (actions) {
            is HomeScreenActions.ActionSearch -> {
                loadUniversities()
            }
        }
    }

    private fun loadUniversities() {
        viewModelScope.launch {
            getUniversitiesUseCase().collect { resource ->
                homeState = when (resource) {
                    Resource.Loading -> {
                        homeState.copy(isLoading = true)
                    }

                    is Resource.Error -> {
                        homeState.copy(isLoading = false, isError = true, error = resource.errorMsg)
                    }

                    is Resource.Success -> {
                        homeState.copy(isLoading = false, isError = false, characters = resource.data)
                    }
                }
            }
        }

    }
}