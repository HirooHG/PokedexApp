package fr.hiroohg.project_qualdev.view

import android.view.Surface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import fr.hiroohg.project_qualdev.ui.theme.typesColors
import fr.hiroohg.project_qualdev.ui.theme.typesImages
import fr.hiroohg.project_qualdev.view_model.PokemonUiState

@Composable
fun PokemonView(
  id: String?,
  navController: NavHostController,
  pokemonUiState: PokemonUiState
) {
  val pokemons = (pokemonUiState as PokemonUiState.Success).pokemons;
  val pokemon = pokemons.single { it.id == (id?.toInt() ?: 1) }
  val color = typesColors[pokemon.types.first()]!!
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly,
    modifier = Modifier
      .fillMaxSize()
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      AsyncImage(
        model = pokemon.image,
        contentDescription = pokemon.name
      )
      LazyRow(
        modifier = Modifier
          .padding(top = 30.dp)
      ) {
        items(pokemon.types) {
          Image(
            painter = painterResource(id = typesImages[it]!!),
            contentDescription = pokemon.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
              .size(100.dp)
          )
        }
      }
    }
    Row(
      horizontalArrangement = Arrangement.SpaceAround,
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.fillMaxWidth()
    ) {
      IconButton(
        modifier = Modifier
          .border(
            width = 2.dp,
            shape = RoundedCornerShape(50.dp),
            color = Color.White)
          .size(50.dp),
        onClick = {
          if (pokemon.evolutions.before.isNotEmpty()) {
            navController.navigate("Pokemon/${pokemon.evolutions.before.last()}")
          }
        }
      ) {
        Icon(Icons.Filled.ArrowBack, contentDescription = "previous evolution")
      }
      Text(
        text = pokemon.name,
        fontSize = 25.sp
      )
      IconButton(
        modifier = Modifier
          .border(
            width = 2.dp,
            shape = RoundedCornerShape(50.dp),
            color = Color.White)
          .size(50.dp),
        onClick = {
          if (pokemon.evolutions.after.isNotEmpty()) {
            navController.navigate("Pokemon/${pokemon.evolutions.after.first()}")
          }
        }
      ) {
        Icon(Icons.Filled.ArrowForward, contentDescription = "next evolution")
      }
    }
    Text(
      text = pokemon.description
    )
  }
}
