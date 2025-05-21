package com.example.scribbledash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.scribbledash.presentation.DifficultyScreen
import com.example.scribbledash.presentation.DrawScreen
import com.example.scribbledash.presentation.DrawViewModel
import com.example.scribbledash.presentation.HomeScreen
import kotlinx.serialization.Serializable

data class BottomNavigationItem(
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
)

@Composable
fun Navigation() {
    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            selectedIcon = painterResource(R.drawable.icon_graph_selected),
            unselectedIcon = painterResource(R.drawable.icon_graph_unselected)
        ),
        BottomNavigationItem(
            selectedIcon = painterResource(R.drawable.icon_home_selected),
            unselectedIcon = painterResource(R.drawable.icon_home_unselected)
        ),
    )

    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != null && currentRoute == Home::class.java.name)
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.background,
                ) {
                    bottomNavigationItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = index == 0,
                            onClick = { /*TODO*/ },
                            icon = {
                                Image(
                                    painter = if (index == 1) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = null
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent,
                            )
                        )
                    }
                }
        }
    ) { padding ->
        NavHost(navController, startDestination = Home) {
            composable<Home> {
                HomeScreen(
                    onCardClick = { navController.navigate(DifficultyLevel) },
                    modifier = Modifier.padding(padding)
                )
            }
            composable<DifficultyLevel> {
                DifficultyScreen(
                    onCloseClick = { navController.popBackStack() },
                    onBeginnerClick = { navController.navigate(Draw) },
                    onChallengingClick = { navController.navigate(Draw) },
                    onMasterClick = { navController.navigate(Draw) },
                    modifier = Modifier.padding(padding)
                )
            }
            composable<Draw> {
                val viewModel = viewModel<DrawViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                DrawScreen(
                    paths = state.paths,
                    undoPaths = state.undoPaths,
                    currentPath = state.currentPath,
                    onAction = viewModel::onAction,
                    onCloseClick = { navController.navigate(Home) { popUpTo(Home) { inclusive = true } } },
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}

@Serializable
object Home

@Serializable
object DifficultyLevel

@Serializable
object Draw