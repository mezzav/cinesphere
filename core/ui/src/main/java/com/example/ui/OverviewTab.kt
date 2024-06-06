package com.example.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assets.R

@Composable
fun OverviewTab(
    overview: String,
    title: String,
    modifier: Modifier = Modifier,
    spacing: Dp = 50.dp,
    titleFontWeight: FontWeight = FontWeight.Bold,
    titleFontSize: TextUnit = 20.sp,
    metadata: @Composable () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy((-14).dp)
            ) {
                IconButtonWithText(
                    text = "Track",
                    iconPainter = painterResource(id = R.drawable.track),
                    onClick = { }
                )

                IconButtonWithText(
                    text = "Visit Homepage",
                    iconPainter = painterResource(id = R.drawable.home),
                    onClick = { }
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                metadata()
            }
        }

        Spacer(modifier = Modifier.height(spacing))

        Text(
            text = title,
            fontWeight = titleFontWeight,
            fontSize = titleFontSize
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(overview)
    }
}

@Preview(showBackground = true)
@Composable
fun OverviewTabPreview() {
   OverviewTab(
       overview =
            "Princess Leia is captured and held hostage by the evil Imperial forces in their " +
            "effort to take over the galactic Empire. Venturesome Luke Skywalker and dashing " +
            "captain Han Solo team together with the loveable robot duo R2-D2 and C-3PO to rescue" +
            " the beautiful princess and restore peace and justice in the Empire.",
       title = "Overview",
       modifier = Modifier
           .fillMaxWidth()
           .padding(start = 8.dp, end = 8.dp, top = 8.dp)
   ) {
       IconText(
           painter = painterResource(R.drawable.person),
           text = "George Lucas"
       )
   }
}