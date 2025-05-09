package com.jagteshwar.shoppingapp.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagteshwar.domain.model.Product
import com.jagteshwar.domain.network.ResultWrapper
import com.jagteshwar.domain.usecase.GetCategoryUseCase
import com.jagteshwar.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getProductUseCase: GetProductUseCase,
    private val getCategoryUseCase: GetCategoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUiEvents>(HomeScreenUiEvents.Loading)
    val uiState: StateFlow<HomeScreenUiEvents> = _uiState.asStateFlow()

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUiEvents.Loading
            val featured = getProduct("electronics")
            val popularProducts = getProduct("jewelery")
            val categories = getCategory()
            if(featured.isEmpty() && popularProducts.isEmpty() && categories.isNotEmpty()){
                _uiState.value = HomeScreenUiEvents.Error("Failed to load products.")
                return@launch
            }
            _uiState.value = HomeScreenUiEvents.Success(
                featured = featured,
                popularProducts = popularProducts,
                categoryList = categories
            )
        }
    }

    private suspend fun getCategory(): List<String> {
        getCategoryUseCase.execute().let { result->
            when(result){
                is ResultWrapper.Failure -> {
                    return emptyList()
                }
                is ResultWrapper.Success -> {
                    return result.value
                }
            }
        }
    }

    private suspend fun getProduct(category: String?): List<Product> {
        getProductUseCase.execute(category).let { result ->
            when (result) {
                is ResultWrapper.Failure -> {
                    return emptyList()
                }

                is ResultWrapper.Success -> {
                    return result.value

                }
            }
        }
    }
}

sealed class HomeScreenUiEvents {
    data object Loading : HomeScreenUiEvents()
    data class Success(
        val featured: List<Product>,
        val popularProducts: List<Product>,
        val categoryList: List<String> = emptyList()
    ) :
        HomeScreenUiEvents()

    data class Error(val message: String) : HomeScreenUiEvents()
}