package com.example.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.model.interfaces.ExternalOverviewModel

@Composable
fun<T : ExternalOverviewModel> PagerItemList(
    title: String,
    items: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    titleFontSize: TextUnit = 24.sp,
    navigateToDetails: (Int) -> Unit,
) {
    Text(
        text = title,
        fontSize = titleFontSize,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier)

    Box(modifier = Modifier.fillMaxWidth()) {
        when (items.loadState.refresh) {
            LoadState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(count = items.itemCount) { index ->
                        val item = items[index]

                        if (item != null) {
                            PagerItemCard(
                                posterUrl = item.posterUrl,
                                onClickMoreOptions =  { /* TODO */ },
                                boxModifier = Modifier
                                    .size(width = 120.dp, height = 180.dp),
                                imageModifier = Modifier
                                    .clickable {
                                        navigateToDetails(item.id)
                                    }
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        }
                    }

                    item {
                        if (items.loadState.append is LoadState.Loading) {
                            Log.d("PAGER_ROW_CORE", "LoadState.Loading")
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }

    Spacer(modifier)
}

@Composable
fun PagerItemCard(
    posterUrl: String?,
    onClickMoreOptions: () -> Unit,
    boxModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    placeholder: Painter? = null,
    contentDescription: String? = null
) {
    Box(
        modifier = boxModifier
    ) {
       AsyncImage(
           model = ImageRequest.Builder(LocalContext.current)
               .data(posterUrl)
               .crossfade(true)
               .build(),
           placeholder = placeholder,
           contentScale = ContentScale.Crop,
           contentDescription = contentDescription,
           modifier = imageModifier
       )

        IconButton(
            onClick = { onClickMoreOptions },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(R.string.more_options),
                tint = Color.White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PagerItemCardPreview() {
    PagerItemCard(
        posterUrl = "https://example.com/image.jpeg",
        placeholder = painterResource(id = R.drawable.star_wars_poster),
        onClickMoreOptions = { /*TODO*/ },
        boxModifier = Modifier
            .size(width = 120.dp, height = 180.dp),
        imageModifier =  Modifier
            .clip(RoundedCornerShape(8.dp))
    )
}