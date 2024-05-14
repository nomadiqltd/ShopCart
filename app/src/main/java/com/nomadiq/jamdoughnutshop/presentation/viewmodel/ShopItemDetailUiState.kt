package com.nomadiq.jamdoughnutshop.presentation.viewmodel

import com.nomadiq.jamdoughnutshop.domain.model.ShopItemDetail

/**
 *  @author Michael Akakpo
 *
 *  Representing the uiState of the [ShopItemDetail], this can be utilised in the relevant @Composable
 *
 */
data class ShopItemDetailUiState(
    val item: ShopItemDetail = ShopItemDetail(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
)
