package com.example.cinesphere.ui.tv.overview

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.cinesphere.R
import com.example.utils.NavigationHomeDestination

object TVOverviewDestination: NavigationHomeDestination {
    override val route = "tv_overview"
    override val titleRes = R.string.tv_overview_title
    override val drawable = R.drawable.tv
}

@Composable
fun TVOverviewScreen() {
    Text("Hello from TV Overview")
}