package com.example.cinesphere

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cinesphere.ui.navigation.CineSphereNavigationGraph
import com.example.cinesphere.ui.search.SearchDestination
import com.example.cinesphere.ui.theme.CineSphereTheme
import com.example.movie.navigation.MovieOverviewDestination
import com.example.tv.navigation.TVOverviewDestination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CineSphereTheme {
                // A surface container using the 'background' color from the theme
                CineSphereBottomAppBar()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CineSphereBottomAppBar() {
    val navController = rememberNavController()

    val items = listOf(
        MovieOverviewDestination,
        TVOverviewDestination,
        SearchDestination
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach{ screen ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {it.route == screen.route} == true,
                        onClick = {
                          navController.navigate(screen.route)
                        },
                        icon = {
                            Icon(
                                painterResource(id = screen.drawable),
                                contentDescription = stringResource(id = screen.titleRes)
                            )
                        },
                        label = {
                            Text(
                                stringResource(id = screen.titleRes),
                                fontSize = 12.sp
                            )
                        },
                    )
                }

            }
        }
    ) {
        CineSphereNavigationGraph(navController = navController)
    }
}