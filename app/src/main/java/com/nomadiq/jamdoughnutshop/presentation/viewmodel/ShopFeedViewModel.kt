package com.nomadiq.jamdoughnutshop.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomadiq.jamdoughnutshop.domain.model.CategoryItem
import com.nomadiq.jamdoughnutshop.domain.usecase.GetShopFeedUseCase
import com.nomadiq.jamdoughnutshop.domain.model.ShopFeedItem
import com.nomadiq.jamdoughnutshop.domain.usecase.GetShopCategoryFilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * @author - Michael Akakpo
 *
 * [ShopFeedViewModel]
 *
 * Defines UI State information about the list of [ShopFeedItem]
 * retrieved from the API and make it accessible to Compose and other UI components
 *
 */

@HiltViewModel
class ShopFeedViewModel @Inject constructor(
    private val getShopFeedUseCase: GetShopFeedUseCase,
    private val getShopCategoryUseCase: GetShopCategoryFilterUseCase,
) :
    ViewModel() {
    companion object {
        const val NETWORK_ERROR_MESSAGE = "Connectivity issues, please check your connection"
    }

    private val _uiState =
        MutableStateFlow(
            ShopFeedUiState(
                items = listOf(),
                filters = listOf(),
            )
        )
    val uiState: StateFlow<ShopFeedUiState> = _uiState.asStateFlow()

    init {
        onDisplayCategoryFilter()
        onDisplayNewsArticleFeedList()
    }

    // Function to fetch List of [ShopFeedItem] - save success response and update uiState
    private fun onDisplayNewsArticleFeedList() {
        Timber.d("displayNewsArticleFeedList")
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getShopFeedUseCase.invoke().collect { result ->
                _uiState.update { currentState ->
                    currentState.copy(
                        items = result.map {
                            ShopFeedItem(
                                id = it.id,
                                name = it.name,
                                price = it.price,
                                imgUrl = it.imgUrl,
                            )
                        },
                    )
                }
                // Stop loading regardless of branch entered
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun onDisplayCategoryFilter() {
        Timber.d("displayCategoryFilterList")
        viewModelScope.launch {
            getShopCategoryUseCase.invoke().collect { result ->
                _uiState.update { currentState ->
                    currentState.copy(
                        filters = result.map {
                            CategoryItem(it.category_id, it.label)
                        },
                    )
                }
                // Stop loading regardless of branch entered
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}
