package com.nomadiq.jamdoughnutshop.presentation.ui.screens

import android.content.res.Configuration
import android.text.util.Linkify
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.rememberAsyncImagePainter
import com.google.android.material.textview.MaterialTextView
import com.nomadiq.jamdoughnutshop.domain.model.ShopItemDetail
import com.nomadiq.jamdoughnutshop.presentation.ui.component.NewsTopAppBar
import com.nomadiq.jamdoughnutshop.presentation.ui.theme.JamDoughnutTheme
import com.nomadiq.jamdoughnutshop.presentation.viewmodel.ShopItemDetailUiState

/**
 * @author - Michael Akakpo
 *
 * The detail screen which displays an [ShopItemDetail]
 */


@Composable
fun NewsArticleItemDetailScreen(
    uiState: ShopItemDetailUiState,
    canNavigateBack: Boolean = false,
    onNavigateUp: () -> Unit = {},
) {
    JamDoughnutTheme {
        Scaffold(
            topBar = {
                NewsTopAppBar(
                    title = uiState.item.name,
                    canNavigateBack = canNavigateBack,
                    navigateUp = { onNavigateUp() },
                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
                content = {

                    // Loading State
                    OnLoadingState(uiState)

                    ArticleItemDetailImage(imgUrl = uiState.item.imgUrl)
                    Spacer(modifier = Modifier.padding(8.dp))
                    ArticleItemDetailTitle(title = uiState.item.name)
                    Spacer(modifier = Modifier.padding(8.dp))
                    ArticleItemDetailBody(body = uiState.item.description)
                    Spacer(modifier = Modifier.padding(8.dp))
                    ArticleItemDetailTitle(title = uiState.item.price.toString())
                    Spacer(modifier = Modifier.padding(8.dp))
                    ProductQuantitySelector()
                    Spacer(modifier = Modifier.padding(8.dp))
                    ShopItemDetailCTA()
                }
            )
        }
    }
}

@Preview(name = "ArticleItemTitle (light)")
@Preview("ArticleItemTitle (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ArticleItemDetailTitle(modifier: Modifier = Modifier, title: String = "Sky News") {
    Text(
        modifier = Modifier.padding(start = 16.dp, end = 0.dp, bottom = 8.dp),
        style = MaterialTheme.typography.titleLarge,
        text = title,
    )
}

@Preview(name = "ArticleItemDetailBody (light)")
@Preview("ArticleItemDetailBody (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ArticleItemDetailBody(modifier: Modifier = Modifier, body: String = "Loreum ipsum") {

    AndroidView(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        factory = {
            MaterialTextView(it).apply {
                // links
                autoLinkMask = Linkify.WEB_URLS
                linksClickable = true
                // setting the color to use for highlighting the links
                setLinkTextColor(Color.Blue.toArgb())
                setTextColor(Color.LightGray.toArgb())
            }
        },
        update = { it.text = HtmlCompat.fromHtml(body, 0) }
    )
}


@Preview(name = "ArticleItemDetailImage (light)")
@Preview("ArticleItemDetailImage (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ArticleItemDetailImage(
    modifier: Modifier = Modifier,
    imgUrl: String = "https://source.unsplash.com/random/500x500?sig=1"
) {
    Image(
        painter = rememberAsyncImagePainter(
            model = imgUrl,
        ),
        contentDescription = "article image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(ratio = 3f / 2f, matchHeightConstraintsFirst = true)
            .clip(MaterialTheme.shapes.large),
    )
    Spacer(modifier = Modifier.padding(8.dp))
}

@Composable
private fun OnLoadingState(uiState: ShopItemDetailUiState) {
    var loading by rememberSaveable { mutableStateOf(false) }
    loading = uiState.isLoading
    if (loading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            CircularProgressIndicator()
        }
    }
}


@Preview(name = "ShopItemDetailCTA (light)")
@Preview("ShopItemDetailCTA (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ShopItemDetailCTA(
    modifier: Modifier = Modifier,
) {
    Spacer(modifier = Modifier.padding(8.dp))
    Button(onClick = { /* Handle button click */ }) {
        Text(text = "Buy Now")
    }
}


@Composable
fun ProductQuantitySelector(
) {

    Row {
        Button(onClick = { }) {
            Text(text = "-")
        }

        Spacer(modifier = Modifier.padding(start = 8.dp, end = 0.dp))
        Text(text = "1")
        Spacer(modifier = Modifier.padding(start = 0.dp, end = 8.dp, top = 8.dp))
        Button(onClick = {}) {
            Text(text = "+")
        }
    }
}