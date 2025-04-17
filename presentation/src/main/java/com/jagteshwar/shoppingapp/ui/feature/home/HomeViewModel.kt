package com.jagteshwar.shoppingapp.ui.feature.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagteshwar.domain.model.Product
import com.jagteshwar.domain.network.ResultWrapper
import com.jagteshwar.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getProductUseCase: GetProductUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUiEvents>(HomeScreenUiEvents.Loading)
    val uiState: StateFlow<HomeScreenUiEvents> = _uiState.asStateFlow()

    init {
        getProduct()
    }

    fun getProduct(){
        viewModelScope.launch {
            _uiState.value = HomeScreenUiEvents.Loading
            getProductUseCase.execute().let { result->
                when(result){
                    is ResultWrapper.Failure -> {
                        val message = (result as ResultWrapper.Failure).exception.message ?: "An Error Occurred."
                        _uiState.value = HomeScreenUiEvents.Error(message)
                    }
                    is ResultWrapper.Success -> {
                        val data = (result as ResultWrapper.Success).value
                        _uiState.value = HomeScreenUiEvents.Success(data)
                    }
                }
            }
        }
    }
}

sealed class HomeScreenUiEvents {
    data object Loading: HomeScreenUiEvents()
    data class Success(val data: List<Product>): HomeScreenUiEvents()
    data class Error(val message: String): HomeScreenUiEvents()
}