package fr.hiroohg.project_qualdev.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import fr.hiroohg.project_qualdev.model.model.Pokemon
import fr.hiroohg.project_qualdev.view_model.PokemonUiState
import fr.hiroohg.project_qualdev.R
import fr.hiroohg.project_qualdev.ui.theme.typesColors
import fr.hiroohg.project_qualdev.ui.theme.typesImages
import kotlin.reflect.typeOf

@Composable
fun PokemonsView(
    navController: NavHostController,
    pokemonUiState: PokemonUiState
) {
    when(pokemonUiState) {
        is PokemonUiState.Loading -> LoadingView()
        is PokemonUiState.Error -> ErrorView(pokemonUiState)
        is PokemonUiState.Success -> ListView(pokemonUiState, navController)
    }
}

@SuppressLint("RestrictedApi")
@Composable
fun ListView(
    state: PokemonUiState.Success,
    navController: NavHostController
) {
    val pokemons = state.pokemons
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        items(pokemons) { i ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = typesColors[i.types.first()] ?: Color(0, 117, 190),
                ),
                modifier = Modifier
                    .clickable(onClick = { navController.navigate("Pokemon/${i.id}") })
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = i.name,
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    LazyRow {
                        items(i.types) {j ->
                            Image(
                                painter = painterResource(id = typesImages[j]!!),
                                contentDescription = i.name,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(50.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

/// Pokemon Card


/// Error and loading
/// TODO: Refacto
@Composable
fun ErrorView(
    state: PokemonUiState.Error
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Card(
            modifier = Modifier
                .size(200.dp)
                .padding(10.dp),
            shape = CircleShape
        ) {
            Image(
                painterResource(R.drawable.poke_photo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(text = "There has been an error with the data, please contact the owner")
    }
}

@Composable
fun LoadingView() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Card(
            modifier = Modifier
                .size(200.dp)
                .padding(10.dp),
            shape = CircleShape
        ) {
            Image(
                painterResource(R.drawable.poke_photo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(text = "Wainting for pokemons to arrive...")
    }
}

