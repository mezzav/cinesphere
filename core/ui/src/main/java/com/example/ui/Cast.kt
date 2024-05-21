package com.example.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.model.interfaces.ProductionMember


@Composable
fun<T : ProductionMember> ProductionMemberList(
    productionMembers: List<T>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(productionMembers) {member ->
            ProductionMemberItem(
                profilePhotoUrl = member.profileUrl,
                name = member.name,
                role = member.role,
                modifier = modifier
            )

            HorizontalDivider()
        }
    }
}

@Composable
fun ProductionMemberItem(
    profilePhotoUrl: String?,
    name:String,
    role: String,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = {
            Text(
                text = name,
                fontWeight = FontWeight.Bold
            )
        },
        leadingContent = {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(profilePhotoUrl)
                    .crossfade(true)
                    .error(R.drawable.person)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = modifier
            )
        },
        supportingContent = {
            Text(
                text = role,
                color = Color.Gray
            )
        },
    )
}