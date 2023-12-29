package fr.hiroohg.project_qualdev.view.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var route = currentDestination?.hierarchy?.first()?.route ?: "Pokemons"
    route.split("/").first().also { route = it }
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.secondary
        ),
        title = {
            Text(text = route, fontWeight = FontWeight.Bold, fontSize = 30.sp)
        },
         navigationIcon = {
             if (route == "Pokemon") {
                 IconButton(
                     onClick = {
                         navController.navigate("Pokemons")
                     }
                 ) {
                     Icon(
                         Icons.Filled.ArrowBack,
                         contentDescription = "Back in menu",
                         tint = MaterialTheme.colorScheme.secondary
                     )
                 }
             }
         }
    )
}