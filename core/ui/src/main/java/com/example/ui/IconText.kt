package com.example.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IconText(
    painter: Painter,
    text: String,
    modifier: Modifier = Modifier,
    spacing: Dp = 4.dp,
    maxLines: Int = 1,
    textOverflow: TextOverflow = TextOverflow.Ellipsis,
    contentDescription: String? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription
        )

        Spacer(Modifier.width(spacing))

        Text(
            text = text,
            maxLines = maxLines,
            overflow = textOverflow
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IconTextPreview() {
    IconText(
        painter = painterResource(id = R.drawable.person),
        text = "George Lucas",
        contentDescription = "Director"
    )
}