package com.example.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IconButtonWithText(
    text: String,
    onClick: () -> Unit,
    iconPainter: Painter,
    spacing: Modifier = Modifier.width(8.dp)
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ),
        contentPadding = PaddingValues(0.dp)
    ) {

        Icon(painter = iconPainter, contentDescription = null )

        Spacer(modifier = spacing)

        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun IconButtonWithTextPreview() {
    IconButtonWithText(
        text = "Visit Homepage",
        iconPainter = painterResource(id = com.example.assets.R.drawable.home),
        onClick = { }
    )
}