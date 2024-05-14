package com.nomadiq.jamdoughnutshop.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nomadiq.jamdoughnutshop.presentation.ui.navigation.NavigationGraph
import com.nomadiq.jamdoughnutshop.presentation.ui.theme.JamDoughnutTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 *  @author Michael Akakpo
 *
 *  MainActivity hosting the entry point to the application
 *
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JamDoughnutTheme {
                NavigationGraph()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JamDoughnutTheme {
        NavigationGraph()
    }
}

