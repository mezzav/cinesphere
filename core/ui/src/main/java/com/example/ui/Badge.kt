package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Badge(
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(
        topStart = 8.dp,
        topEnd = 8.dp,
        bottomEnd = 8.dp,
        bottomStart = 8.dp
    ),
    backgroundColor: Color,
    borderColor: Color,
    contentColor: Color = Color.Black
) {
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = shape
            )
            .border(
                width = 0.5.dp,
                color = borderColor,
                shape = shape
            )
            .padding(horizontal = 6.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = contentColor,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BadgePreview() {
    Badge(
        "Success",
        backgroundColor = colorResource(id = com.example.assets.R.color.successColor),
        borderColor = colorResource(id = com.example.assets.R.color.emeraldColor)
    )
}