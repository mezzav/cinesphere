package com.example.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest

interface ExternalOverviewModel {
    val id: Int
    val posterUrl: String?
}

@Composable
fun<T : ExternalOverviewModel> PagerItemList(
    title: String,
    items: LazyPagingItems<T>,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = items.itemCount) { index ->
            val item = items[index]

            if (item != null) {
                PagerItemCard(
                    posterUrl = item.posterUrl,
                    onClickMoreOptions =  { /* TODO */ },
                    modifier = Modifier.clickable {
                        navigateToDetails(item.id)
                    }.clip(RoundedCornerShape(8.dp))
                )
            }
        }
    }

    Spacer(modifier)
}

@Composable
fun PagerItemCard(
    posterUrl: String?,
    onClickMoreOptions: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(posterUrl)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = modifier
        )

        IconButton(
            onClick = { onClickMoreOptions },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}