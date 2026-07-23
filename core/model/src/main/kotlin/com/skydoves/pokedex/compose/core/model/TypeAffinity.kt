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

package com.skydoves.pokedex.compose.core.model

// Valor de categoria de cada tipo de Pokemon.
// Tipos mais raros valem mais.
fun valorCategoria(tipo: String): Int {
  return when (tipo) {
    "dragon", "ghost", "steel" -> 10
    "fire", "water", "electric", "ice", "fairy" -> 8
    "grass", "poison", "ground", "flying", "psychic", "dark" -> 6
    "normal", "fighting", "bug", "rock" -> 4
    else -> 0
  }
}

// Multiplicador aplicado com base no tipo primario.
fun multiplicadorTipo(tipoPrimario: String): Double {
  return when (tipoPrimario) {
    "fire", "water", "electric" -> 1.35
    "grass", "bug", "poison" -> 0.75
    else -> 1.00
  }
}
