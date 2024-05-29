package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun Header(
    backdropUrl: String?,
    modifier: Modifier = Modifier,
    errorImage: Int = R.drawable.star_wars,
    previewPlaceholder: Int = R.drawable.star_wars,
    contentDescription: String? = null,
) {
    var size by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(backdropUrl)
                .error(errorImage)
                .crossfade(true)
                .build(),
            placeholder =  painterResource(id = previewPlaceholder),
            contentDescription = contentDescription,
            modifier = Modifier.onGloballyPositioned {
                size = it.size
            }
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = size.height.toFloat() / 4,
                        endY = size.height.toFloat()
                    ),
                )
        ) { }

    }
}

@Composable
fun TitleCard(
    title: String,
    genres: List<String>,
    tagline: String?,
    modifier: Modifier = Modifier,
    titleFontSize: TextUnit = 24.sp,
    subtitleFontSize: TextUnit = 11.sp,
    textColor: Color = Color.White,
) {
    val genresToDisplay = genres.joinToString(separator = " ${Typography.bullet} ")

    Box(
        modifier = modifier
    ) {
        Column {
            Text(
                text = title,
                color = textColor,
                fontSize = titleFontSize,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = genresToDisplay,
                    color = textColor,
                    fontSize = subtitleFontSize,
                    modifier = Modifier.weight(1f)
                )

                tagline?.let {
                    Text(
                        text = it,
                        color = textColor,
                        fontSize = subtitleFontSize,
                        textAlign = TextAlign.End,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BackdropPreview() {
    Box {
        Header(
            backdropUrl = "https://example.com/image.jpg"
        )

        TitleCard(
            title = "Star Wars",
            genres = listOf(
                "Action",
                "Adventure",
                "Science Fiction"
            ).take(2),
            tagline = "A long time ago in a galaxy far, far away...",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 8.dp, bottom = 20.dp, end = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitleCardPreview() {
    TitleCard(
        title = "Star Wars",
        genres = listOf(
            "Action",
            "Adventure",
            "Science Fiction"
        ).take(2),
        tagline = "A long time ago in a galaxy far, far away...",
        textColor = Color.Black
    )
}
