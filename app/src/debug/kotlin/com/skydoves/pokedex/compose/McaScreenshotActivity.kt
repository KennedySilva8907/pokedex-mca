/*
 * Designed and developed by 2024 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.pokedex.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.CompositionLocalProvider
import com.skydoves.pokedex.compose.core.designsystem.theme.PokedexTheme
import com.skydoves.pokedex.compose.core.model.Pokemon
import com.skydoves.pokedex.compose.core.navigation.LocalComposeNavigator
import com.skydoves.pokedex.compose.core.navigation.PokedexNavigator
import com.skydoves.pokedex.compose.core.navigation.PokedexScreen
import com.skydoves.pokedex.compose.feature.details.PokedexDetails
import dagger.hilt.android.AndroidEntryPoint

/**
 * Debug-only activity used to open a Pokemon details screen by id/name
 * for MCA screenshot capture via adb.
 *
 * Example:
 * adb shell am start -n com.skydoves.pokedex.compose/.McaScreenshotActivity \
 *   --es name bulbasaur --ei id 1
 */
@AndroidEntryPoint
class McaScreenshotActivity : ComponentActivity() {

  @OptIn(ExperimentalSharedTransitionApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()
    super.onCreate(savedInstanceState)

    val name = intent.getStringExtra(EXTRA_NAME) ?: "bulbasaur"
    val id = intent.getIntExtra(EXTRA_ID, 1)
    val pokemon = Pokemon(
      nameField = name,
      url = "https://pokeapi.co/api/v2/pokemon/$id/",
    )

    setContent {
      CompositionLocalProvider(LocalComposeNavigator provides NoOpNavigator) {
        PokedexTheme(darkTheme = false) {
          SharedTransitionLayout {
            AnimatedContent(targetState = pokemon.nameField, label = "mca-screenshot") {
              PokedexDetails(
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedContentScope = this,
                pokemon = pokemon,
              )
            }
          }
        }
      }
    }
  }

  private object NoOpNavigator : PokedexNavigator {
    override fun navigate(screen: PokedexScreen) = Unit
    override fun navigateUp(): Boolean = false
  }

  companion object {
    const val EXTRA_NAME = "name"
    const val EXTRA_ID = "id"
  }
}
