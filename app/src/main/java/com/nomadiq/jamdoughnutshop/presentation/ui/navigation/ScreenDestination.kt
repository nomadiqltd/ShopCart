package com.nomadiq.jamdoughnutshop.presentation.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 *
 * @author - Michael Akakpo
 *
 * Predefined set of values that represent all the possible screens destinations in the app nav graph
 *
 */


/**
 * Contract for route / argument data required for every navigation destination
 */
sealed interface ScreenDestination {
    val route: String
}


/**
 * Main Shop Feed screen
 */
data object ShopFeedListScreen : ScreenDestination {
    override val route = "home_screen_shop_feed"
}

/**
 * News Item Detail screen. View a single item detail screen
 */
data object ShopItemDetailScreen : ScreenDestination {
    override val route = "shop_item"
    private const val ITEM_ID = "id"
    val routeWithArgs = "${route}/{${ITEM_ID}}"

    val arguments = listOf(
        navArgument(name = ITEM_ID) {
            defaultValue = 1
            type = NavType.IntType
        }
    )
}