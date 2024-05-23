package com.example.cinesphere.ui.search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.cinesphere.R
import com.example.utils.NavigationHomeDestination

object SearchDestination: NavigationHomeDestination {
    override val route = "search"
    override val titleRes = R.string.search_title
    override val drawable = R.drawable.search
}
@Composable
fun SearchScreen() {
    Text("Hello from Search Screen")
}