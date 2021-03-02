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
package com.example.androiddevchallenge.ui.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.data.Pet
import com.example.androiddevchallenge.data.petMockData
import com.example.androiddevchallenge.ui.components.NameAgeRow
import com.example.androiddevchallenge.ui.components.NetworkImage

@Composable
fun Detail(pet: Pet) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            NetworkImage(
                url = pet.pictureUrl,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        NameAgeRow(name = pet.name, age = pet.age)
        Spacer(modifier = Modifier.height(16.dp))
        Quote(text = pet.quote)
        Spacer(modifier = Modifier.height(16.dp))
        History(text = pet.history, modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            AdoptButton()
        }
    }
}

@Composable
fun Quote(text: String, modifier: Modifier = Modifier) {
    Surface(color = Color(0x7000AB6F)) {
        Text(
            text = "\"$text\"",
            modifier = modifier
                .padding(32.dp)
                .fillMaxWidth(),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                fontSize = 12.sp
            )
        )
    }
}

@Composable
fun History(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier.fillMaxWidth(),
        style = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    )
}

@Composable
fun AdoptButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        modifier = modifier,
        onClick = {
            Toast.makeText(context, "Adopt me!", Toast.LENGTH_SHORT).show()
        }
    ) {
        Text(text = "Adopt me 😍")
    }
}

@Preview
@Composable
fun QuotePreview() {
    Quote(text = "I like to run, as long as I'm able to!")
}

@Preview
@Composable
fun HistoryPreview() {
    History(text = "Lorem Ipsum")
}

@Preview
@Composable
fun AdoptButtonPreview() {
    AdoptButton()
}

@Preview
@Composable
fun DetailPreview() {
    Detail(pet = petMockData[0])
}
