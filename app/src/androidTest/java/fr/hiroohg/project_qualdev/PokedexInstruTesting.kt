package fr.hiroohg.project_qualdev

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import fr.hiroohg.project_qualdev.model.entities.Evolutions
import fr.hiroohg.project_qualdev.model.entities.Pokemon
import fr.hiroohg.project_qualdev.ui.theme.Project_qualdevTheme
import fr.hiroohg.project_qualdev.view.ErrorView
import fr.hiroohg.project_qualdev.view.ListView
import fr.hiroohg.project_qualdev.view.LoadingView
import fr.hiroohg.project_qualdev.view.PokemonView
import fr.hiroohg.project_qualdev.view.nav.TopBar
import fr.hiroohg.project_qualdev.view_model.PokemonUiState
import org.junit.Rule
import org.junit.Test

val pokemons = listOf(
    Pokemon(0, "pokemon1", "pokemon1", listOf("Plante"), "", Evolutions(listOf(), listOf(1, 2))),
    Pokemon(1, "pokemon2", "pokemon2", listOf("Plante", "Poison"), "", Evolutions(listOf(1), listOf(2))),
    Pokemon(2, "pokemon3", "pokemon3", listOf("Plante", "Poison"), "", Evolutions(listOf(), listOf(1, 2)))
)

class PokedexInstruTesting {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPokemonView() {
        val state = PokemonUiState.Success(pokemons)
        val navController = rememberNavController()
        composeTestRule.setContent {
            Project_qualdevTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Scaffold(topBar = { TopBar(navController) }) {
                        Surface(modifier = Modifier.padding(it)) {
                            PokemonView(0.toString(), navController, state)
                        }
                    }
                }
            }
        }
        composeTestRule.onNodeWithText(pokemons[0].name).assertExists("Nope, no name")
        composeTestRule.onNodeWithText(pokemons[0].description).assertExists("Nope, no description")
    }

    @Test
    fun testCardPokemonSuccess() {
        val state = PokemonUiState.Success(pokemons)
        val navController = rememberNavController()
        val scrollState = rememberLazyListState()
        composeTestRule.setContent {
            Project_qualdevTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Scaffold(topBar = { TopBar(navController) }) {
                        Surface(modifier = Modifier.padding(it)) {
                            ListView(state, navController, scrollState)
                        }
                    }
                }
            }
        }
        composeTestRule.onNodeWithText(pokemons[0].name).assertExists("Nope, no name on card")
    }

    @Test
    fun testCardPokemonLoading() {
        val navController = rememberNavController()
        val scrollState = rememberLazyListState()
        composeTestRule.setContent {
            Project_qualdevTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Scaffold(topBar = { TopBar(navController) }) {
                        Surface(modifier = Modifier.padding(it)) {
                            LoadingView()
                        }
                    }
                }
            }
        }
        composeTestRule.onNodeWithText("Loading some pokemons...").assertExists("Nope, no loading message")
    }

    @Test
    fun testCardPokemonError() {
        val navController = rememberNavController()
        val scrollState = rememberLazyListState()
        composeTestRule.setContent {
            Project_qualdevTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Scaffold(topBar = { TopBar(navController) }) {
                        Surface(modifier = Modifier.padding(it)) {
                            ErrorView(Exception())
                        }
                    }
                }
            }
        }
        composeTestRule.onNodeWithText("There has been an error with the data, please contact the owner").assertExists("Nope, no loading message")
    }
}