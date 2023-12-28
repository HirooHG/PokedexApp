package fr.hiroohg.project_qualdev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import fr.hiroohg.project_qualdev.ui.theme.Project_qualdevTheme
import fr.hiroohg.project_qualdev.PokemonApp
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      Project_qualdevTheme {
        Surface(modifier = Modifier.fillMaxSize().background(Color(223, 223, 223) ), color = MaterialTheme.colorScheme.background) {
          PokemonApp()
        }
      }
    }
  }
}
