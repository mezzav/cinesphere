package com.example.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorIndicator(
    text: String,
    spacing: Dp = 8.dp,
    fontSize: TextUnit = 10.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = Color.Black,
    textModifier: Modifier = Modifier,
    boxModifier: Modifier = Modifier
) {
    Box(modifier = boxModifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.error),
                contentDescription = stringResource(R.string.error_icon),
            )

            Spacer(modifier = Modifier.height(spacing))

            Text(
                text = text,
                fontSize = fontSize,
                fontWeight = fontWeight,
                color = color,
                modifier = textModifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorIndicatorPreview() {
    ErrorIndicator(
        text = "An error occurred.",
    )
}