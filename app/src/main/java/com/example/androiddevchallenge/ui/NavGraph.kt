/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.petMockData
import com.example.androiddevchallenge.ui.detail.Detail
import com.example.androiddevchallenge.ui.main.Main
import com.example.androiddevchallenge.ui.theme.MyTheme

enum class Destination(val literal: String) {
    MAIN("main"),
    DETAIL("detail")
}

@Composable
fun NavGraph(startDestination: Destination = Destination.MAIN) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination.literal) {
        composable(Destination.MAIN.literal) {
            MainWrapper {
                Main(list = petMockData) {
                    navController.navigate("${Destination.DETAIL.literal}/${it.id}")
                }
            }
        }

        composable("${Destination.DETAIL.literal}/{petId}") { backStackEntry ->
            petMockData.firstOrNull { it.id.toString() == backStackEntry.arguments?.getString("petId") }
                ?.let { pet ->
                    DetailWrapper(
                        title = pet.name,
                        actionButton = {
                            navController.popBackStack()
                        }
                    ) {
                        Detail(pet = pet)
                    }
                }
        }
    }
}

@Composable
fun MainWrapper(content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.app_name)) })
        }
    ) {
        content()
    }
}

@Composable
fun DetailWrapper(title: String, actionButton: () -> Unit, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = actionButton) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        content()
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        NavGraph()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        NavGraph()
    }
}
