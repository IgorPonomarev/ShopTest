package com.dviss.shoptest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dviss.shoptest.domain.usecase.AppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val useCases: AppUseCases
): ViewModel() {

    private val _appState = MutableStateFlow(AppState())
    val appState = _appState.asStateFlow()

    init {
        //downloadProducts()
        collectProducts()
    }

    private fun downloadProducts() {
        viewModelScope.launch {
            useCases.downloadProducts()
        }
    }

    private fun collectProducts() {
        viewModelScope.launch {
            useCases.getProducts().collect {
                _appState.value = _appState.value.copy(products = it)
            }
        }
    }
}