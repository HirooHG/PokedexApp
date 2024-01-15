package fr.hiroohg.project_qualdev.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import fr.hiroohg.project_qualdev.model.entities.Pokemon
import fr.hiroohg.project_qualdev.ui.theme.typesColors
import fr.hiroohg.project_qualdev.ui.theme.typesImages
import fr.hiroohg.project_qualdev.view_model.PokemonUiState

@Composable
fun PokemonView(id: String?, navController: NavHostController, pokemonUiState: PokemonUiState) {
  val pokemons = (pokemonUiState as PokemonUiState.Success).pokemons
  val pokemon = pokemons.single { it.id == (id?.toInt() ?: 1) }
  val color = typesColors[pokemon.types.first()]!!
  Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceEvenly,
      modifier = Modifier.fillMaxSize()
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      AsyncImage(model = pokemon.image, contentDescription = pokemon.name)
      LazyRow(modifier = Modifier.padding(top = 30.dp)) {
        items(pokemon.types) {
          Image(
              painter = painterResource(id = typesImages[it]!!),
              contentDescription = pokemon.name,
              contentScale = ContentScale.Fit,
              modifier = Modifier.size(100.dp)
          )
        }
      }
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
      pokemon.evolutions.before.isNotEmpty().let {
        if (it) {
          val evolBeforePokemon = pokemons.single { i -> i.id == pokemon.evolutions.before.last() }
          EvolutionComposable(evolBeforePokemon, navController)
        }
      }
      Text(text = pokemon.name, fontSize = 25.sp)
      pokemon.evolutions.after.isNotEmpty().let {
        if (it) {
          val evolAfterPokemon = pokemons.single { i -> i.id == pokemon.evolutions.after.first() }
          EvolutionComposable(evolAfterPokemon, navController)
        }
      }
    }
    Text(text = pokemon.description)
  }
}

@Composable
fun EvolutionComposable(pokemon: Pokemon, navController: NavHostController) {
  AsyncImage(
      model = pokemon.image,
      contentDescription = "evolution",
      modifier =
          Modifier.size(30.dp)
              .clickable(onClick = { navController.navigate("Pokemon/${pokemon.id}") })
  )
}

