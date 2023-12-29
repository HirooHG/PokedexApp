package fr.hiroohg.project_qualdev.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import fr.hiroohg.project_qualdev.view_model.PokemonUiState
import fr.hiroohg.project_qualdev.R
import fr.hiroohg.project_qualdev.ui.theme.typesColors
import fr.hiroohg.project_qualdev.ui.theme.typesImages
import fr.hiroohg.project_qualdev.view_model.PokemonViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokemonsView(
    navController: NavHostController,
    pokemonVM: PokemonViewModel,
    scrollState: LazyListState
) {
    val pokemonUiState: PokemonUiState = pokemonVM.pokemonUiState
    val pullRefreshState = rememberPullRefreshState(
        refreshing = pokemonUiState == PokemonUiState.Loading,
        onRefresh = pokemonVM::getPokemons
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        when (pokemonUiState) {
            is PokemonUiState.Loading -> LoadingView()
            is PokemonUiState.Error -> ErrorView()
            is PokemonUiState.Success -> ListView(pokemonUiState, navController, scrollState)
        }
        PullRefreshIndicator(
            refreshing = pokemonUiState == PokemonUiState.Loading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            backgroundColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.background
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListView(
    state: PokemonUiState.Success,
    navController: NavHostController,
    scrollState: LazyListState
) {
    val pokemons = state.pokemons
    LazyColumn(
        state = scrollState,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
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
                        items(i.types) { j ->
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

/// Error and loading
@Composable
fun ErrorView() {
    OtherView(text = "There has been an error with the data, please contact the owner")
}

@Composable
fun LoadingView() {
    OtherView(text = "Loading some pokemons...")
}

@Composable
fun OtherView(
    text: String
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
        Text(text = text)
    }
}