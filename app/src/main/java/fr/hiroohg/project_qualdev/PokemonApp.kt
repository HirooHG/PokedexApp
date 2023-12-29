package fr.hiroohg.project_qualdev

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import fr.hiroohg.project_qualdev.view_model.PokemonUiState
import fr.hiroohg.project_qualdev.view_model.PokemonViewModel
import fr.hiroohg.project_qualdev.view.PokemonsView
import fr.hiroohg.project_qualdev.view.PokemonView
import fr.hiroohg.project_qualdev.view.nav.TopBar

@Composable
fun PokemonApp() {
  val navController = rememberNavController()

  Scaffold(
    topBar = {
      TopBar(navController)
    }
  ) {
    Surface(
      modifier = Modifier
        .padding(it)
    ) {
      val pokemonVM: PokemonViewModel = viewModel()
      val pokemonUiState: PokemonUiState = pokemonVM.pokemonUiState
      val scrollState = rememberLazyListState()
      NavHost(navController, startDestination = "Pokemons", Modifier.padding(10.dp)) {
        composable("Pokemons") { PokemonsView(navController, pokemonVM, scrollState) }
        composable("Pokemon/{id}") { entry ->
          PokemonView(entry.arguments?.getString("id"), navController, pokemonUiState)
        }
      }
    }
  }
}
