package com.nomadiq.jamdoughnutshop.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomadiq.jamdoughnutshop.domain.model.ShopItemDetail
import com.nomadiq.jamdoughnutshop.domain.usecase.GetShopItemDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author - Michael Akakpo
 *
 * [ShopItemDetailViewModel]
 *
 * Defines UI State information about the details of the individual news article items [NewsArticleFeedItem]
 *
 */

@HiltViewModel
class ShopItemDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getShopItemDetailUseCase: GetShopItemDetailUseCase
) : ViewModel() {

    // Define UIState variable for [ShopItemDetailResponse] data to map to the equivalent UI Screens and components
    private val _uiState = MutableStateFlow(ShopItemDetailUiState())
    val uiState: StateFlow<ShopItemDetailUiState> = _uiState.asStateFlow()

    private val productId: Int =
        checkNotNull(savedStateHandle.getStateFlow(key = "id", initialValue = 1)).value

    init {
        displayShopItemDetail(productId)
    }

    // Function to fetch List of [ShopItemDetailResponse] - save success response and update uiState
    private fun displayShopItemDetail(productId: Int) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getShopItemDetailUseCase.invoke(productId).collect { result ->
                _uiState.update { currentState ->
                    currentState.copy(
                        item = ShopItemDetail(
                            id = result.id,
                            name = result.name,
                            imgUrl = result.imgUrl,
                            description = result.description,
                            price = result.price
                        ),
                    )
                }

                _uiState.update { currentState -> currentState.copy(isLoading = false) }
            }
        }
    }
}
