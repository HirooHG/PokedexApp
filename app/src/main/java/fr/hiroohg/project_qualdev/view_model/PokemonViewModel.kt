package fr.hiroohg.project_qualdev.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.hiroohg.project_qualdev.model.entities.Pokemon
import fr.hiroohg.project_qualdev.model.service.PokemonsApi
import java.lang.Exception
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// global UI state
// with pokemon list
sealed interface PokemonUiState {
  data class Success(val pokemons: List<Pokemon>) : PokemonUiState
  data class Error(val ex: Exception) : PokemonUiState
  object Loading : PokemonUiState
}

// State manager view model, link between view and model
class PokemonViewModel : ViewModel() {
  // current state
  // update when getPokemon ends or refresh of list
  var pokemonUiState: PokemonUiState by mutableStateOf(PokemonUiState.Loading)
    private set

  init {
    getPokemons()
  }

  // async function calling service
  fun getPokemons() {
    viewModelScope.launch {
      pokemonUiState = PokemonUiState.Loading
      delay(1_000L)
      pokemonUiState =
          try {
            val listResult = PokemonsApi.service.getPokemons()
            PokemonUiState.Success(listResult)
          } catch (e: Exception) {
            PokemonUiState.Error(e)
          }
    }
  }
}
