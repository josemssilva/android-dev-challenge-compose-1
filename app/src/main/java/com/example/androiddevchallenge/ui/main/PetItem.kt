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
package com.example.androiddevchallenge.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Pet
import com.example.androiddevchallenge.data.petMockData
import com.example.androiddevchallenge.ui.components.NameAgeRow
import com.example.androiddevchallenge.ui.components.NetworkImage

@Composable
fun PetItem(
    item: Pet,
    modifier: Modifier
) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp,
        modifier = modifier
    ) {
        Column {
            Surface(
                modifier = Modifier
                    .height(200.dp)
                    .background(color = Color(100, 100, 100, 20))
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    NetworkImage(url = item.pictureUrl) { CircularProgressIndicator() }
                }
            }
            NameAgeRow(name = item.name, age = item.age)
        }
    }
}

@Preview
@Composable
fun PreviewPetItem() {
    PetItem(item = petMockData[0], Modifier)
}
