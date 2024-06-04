package com.example.tv.ui.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ui.ErrorIndicator
import com.example.ui.LoadingIndicator

@Composable
fun TVShowDetailsScreenContainer(uiState: TVShowDetailsUiState) {
    when(uiState) {
        is TVShowDetailsUiState.Success -> {
            Text(uiState.show.name)
        }
        is TVShowDetailsUiState.Loading -> {
            LoadingIndicator(modifier = Modifier.fillMaxSize())
        }
        else -> {
            ErrorIndicator(
                text = "An error occurred displaying the information.",
                boxModifier = Modifier.fillMaxSize()
            )
        }
    }
}