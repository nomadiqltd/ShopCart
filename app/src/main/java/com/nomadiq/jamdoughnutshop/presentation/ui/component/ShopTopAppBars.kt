package com.nomadiq.jamdoughnutshop.presentation.ui.component

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nomadiq.jamdoughnutshop.R
import com.nomadiq.jamdoughnutshop.presentation.ui.theme.JamDoughnutTheme

/**
 * @author Michael Akakpo
 *
 * Composable that displays the topBar and displays back button if back navigation is possible.
 *
 */

@Preview(name = "NewsToolbarTitle (light)")
@Preview("NewsToolbarTitle (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NewsToolbarTitle(
    modifier: Modifier = Modifier,
    @StringRes defaultTitle: Int = R.string.toolbar_title_header,
    title: String = ""
) {
    Text(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        style = MaterialTheme.typography.titleLarge,
        text = title ?: stringResource(id = defaultTitle),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "NewsTopAppBar (light)")
@Preview("NewsTopAppBar (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsTopAppBar(
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit = {},
    onAction: @Composable RowScope.() -> Unit = {},
    @StringRes defaultTitle: Int = R.string.toolbar_title_header,
    title: String = ""
) {
    JamDoughnutTheme {
        CenterAlignedTopAppBar(
            title = {
                NewsToolbarTitle(modifier = modifier, title = title)
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = modifier
                .fillMaxWidth(),
            actions = onAction,
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = title
                        )
                    }
                }
            }
        )
    }
}

