package com.nomadiq.jamdoughnutshop.presentation.ui.screens

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nomadiq.jamdoughnutshop.R
import com.nomadiq.jamdoughnutshop.domain.model.ShopFeedItem
import com.nomadiq.jamdoughnutshop.presentation.ui.component.ArticleFeedItemCard
import com.nomadiq.jamdoughnutshop.presentation.ui.component.NewsTopAppBar
import com.nomadiq.jamdoughnutshop.presentation.ui.theme.JamDoughnutTheme
import com.nomadiq.jamdoughnutshop.presentation.viewmodel.ShopFeedUiState

/**
 *  @author Michael Akakpo
 *
 *  Composable representing the list of [ShopFeedItem] items within the Lazy column
 *
 */

@Preview(name = "MainFeedScreen (light)")
@Preview("MainFeedScreen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainFeedScreen(
    modifier: Modifier = Modifier,
    onItemClick: (ShopFeedItem) -> Unit = {},
    onItemBookmarked: (ShopFeedItem) -> Unit = {},
    onItemShared: (ShopFeedItem) -> Unit = {},
    uiState: ShopFeedUiState = ShopFeedUiState(
        items = listOf(),
        filters = listOf(),
    ),
    @StringRes title: Int = R.string.toolbar_title_header
) {
    MainFeedScaffoldState(
        modifier = Modifier,
        title = title,
        uiState = uiState,
        onItemClick = onItemClick,
        onItemBookmarked = onItemBookmarked,
        onItemShared = onItemShared,
    )
}

@Composable
private fun MainFeedScaffoldState(
    modifier: Modifier = Modifier,
    title: Int,
    uiState: ShopFeedUiState,
    onItemClick: (ShopFeedItem) -> Unit = {},
    onItemBookmarked: (ShopFeedItem) -> Unit = {},
    onItemShared: (ShopFeedItem) -> Unit = {},
) {
    JamDoughnutTheme {
        Scaffold(
            topBar = {
                NewsTopAppBar(
                    title = stringResource(id = title),
                    onAction = {
                    },
                )
            }
        ) { paddingValues ->
            MainScaffoldContentView(
                modifier,
                uiState,
                paddingValues,
                onItemClick,
                onItemBookmarked,
                onItemShared
            )
        }
    }
}

@Composable
private fun MainScaffoldContentView(
    modifier: Modifier = Modifier,
    uiState: ShopFeedUiState = ShopFeedUiState(
        items = listOf(),
        filters = listOf(),
    ),
    paddingValues: PaddingValues,
    onItemClick: (ShopFeedItem) -> Unit = {},
    onItemBookmarked: (ShopFeedItem) -> Unit = {},
    onItemShared: (ShopFeedItem) -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding((paddingValues))
    ) {

        // Chip row filter
        FilterProductsChipRow(uiState)

        // Loading state

        // Article Item Feed
        ShopFeed(uiState.items, onItemClick, onItemBookmarked, onItemShared)
    }
}

@Preview(name = "NewsArticleFeed (light)")
@Preview("NewsArticleFeed (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ShopFeed(
    items: List<ShopFeedItem> = listOf(),
    onItemClick: (ShopFeedItem) -> Unit = {},
    onItemBookmarked: (ShopFeedItem) -> Unit = {},
    onItemShared: (ShopFeedItem) -> Unit = {},
) {
    val state: LazyListState = rememberLazyListState()

    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items.forEach { item ->
            item {
                ArticleFeedItemCard(
                    title = item.name,
                    subtitle = item.price.toString(),
                    imgUrl = item.imgUrl,
                    item = item,
                    onItemClick = onItemClick,
                    onItemBookmarked = onItemBookmarked,
                    onItemShared = onItemShared,
                )
            }
        }
    }
}

@Composable
fun FilterProductsChipRow(uiState: ShopFeedUiState) {
    Row {
        FilterChip(
            selected = false,
            onClick = { /* Handle chip click */ },
            label = { Text(text = "Electronics") }
        )
        FilterChip(
            selected = true,
            onClick = { /* Handle chip click */ },
            label = { Text(text = "Clothing") }
        )
        FilterChip(
            selected = false,
            onClick = { /* Handle chip click */ },
            label = { Text(text = "Books") }
        )
    }
}