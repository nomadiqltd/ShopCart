package com.nomadiq.jamdoughnutshop.presentation.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nomadiq.jamdoughnutshop.presentation.ui.screens.NewsArticleItemDetailScreen
import com.nomadiq.jamdoughnutshop.presentation.ui.screens.MainFeedScreen
import com.nomadiq.jamdoughnutshop.presentation.viewmodel.ShopFeedViewModel
import com.nomadiq.jamdoughnutshop.presentation.viewmodel.ShopItemDetailViewModel

/**
 *  @author Michael Akakpo
 *
 *  Navigation graph containing Composable and NavHost to describe
 *  relationships between the screens within the app
 *
 */

@ExperimentalAnimationApi
@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    startDestination: String = ShopFeedListScreen.route, // TODO()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(
            route = ShopFeedListScreen.route,
        ) {
            val viewModel = hiltViewModel<ShopFeedViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            MainFeedScreen(
                uiState = uiState,
                onItemClick = { item ->
                    navController.navigateToItemDetailScreen(item.id)
                },
                onItemBookmarked = {
                    /* TODO() - call Bookmark function */
                },
                onItemShared = {
                    /* TODO() - call Share function */
                }
            )
        }
        composable(
            route = ShopItemDetailScreen.routeWithArgs,
            arguments = ShopItemDetailScreen.arguments,
        ) {
            val shopItemDetailViewModel = hiltViewModel<ShopItemDetailViewModel>()
            val uiState by shopItemDetailViewModel.uiState.collectAsState()
            NewsArticleItemDetailScreen(
                uiState = uiState,
                canNavigateBack = navController.previousBackStackEntry != null,
                onNavigateUp = {
                    navController.navigateUp()
                },
            )
        }
    }
}

// Navigate to a specific screen (argument free)
fun NavHostController.navigateTo(route: String) =
    this.navigate(route) { launchSingleTop = true }

// View Single item / article detail
private fun NavHostController.navigateToItemDetailScreen(productId: Int) {
    this.navigateTo(
        route = "${ShopItemDetailScreen.route}/${productId}"
    )
}

