package fr.hiroohg.project_qualdev.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.hiroohg.project_qualdev.model.model.Pokemon
import fr.hiroohg.project_qualdev.model.service.PokemonApi
import java.io.IOException
import java.lang.Exception
import kotlinx.coroutines.launch
import retrofit2.HttpException

sealed interface PokemonUiState {
  data class Success(val pokemons: List<Pokemon>) : PokemonUiState
  data class Error(val ex: Exception) : PokemonUiState
  object Loading : PokemonUiState
}

class PokemonViewModel : ViewModel() {
  var pokemonUiState: PokemonUiState by mutableStateOf(PokemonUiState.Loading)
    private set

  init {
    getPokemons()
  }

  fun getPokemons() {
    viewModelScope.launch {
      pokemonUiState = PokemonUiState.Loading
      pokemonUiState =
          try {
            val listResult = PokemonApi.retrofitService.getPokemons()
            PokemonUiState.Success(listResult)
          } catch (e: Exception) {
            PokemonUiState.Error(e)
          }
    }
  }
}
