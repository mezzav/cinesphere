package com.example.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun LabelText(
    label: String,
    text: String,
    labelFontWeight: FontWeight = FontWeight.Bold,
    labelFontSize: TextUnit = 16.sp,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            text = label,
            fontWeight = labelFontWeight,
            fontSize = labelFontSize
        )
        Text(text)
    }
}

@Composable
fun LabelText(
    label: String,
    items: List<String>,
    labelFontWeight: FontWeight = FontWeight.Bold,
    labelFontSize: TextUnit = 16.sp,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            text = label,
            fontWeight = labelFontWeight,
            fontSize = labelFontSize
        )

        Column {
            items.forEach {text ->
                Text(text)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LabelTextPreview() {
  LabelText("Type", "Scripted")
}

@Preview(showBackground = true)
@Composable
fun LabelTextListPreview() {
    LabelText("Type", listOf("John Smith", "Jane Doe"))
}
