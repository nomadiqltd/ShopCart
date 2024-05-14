package com.nomadiq.jamdoughnutshop.presentation.viewmodel

import com.nomadiq.jamdoughnutshop.domain.model.CategoryItem
import com.nomadiq.jamdoughnutshop.domain.model.ShopFeedItem

/**
 *  @author Michael Akakpo
 *
 *  Representing the Read only uiState of the [ShopFeedViewModel], this can be utilised by the relevant @Composable
 *
 */
data class ShopFeedUiState(
    val items: List<ShopFeedItem>,
    val filters: List<CategoryItem>,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
) {
}
