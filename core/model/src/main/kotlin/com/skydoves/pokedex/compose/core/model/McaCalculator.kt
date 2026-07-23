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

// Calcula o Indice de Afinidade Dinamica (IAD).
// height e weight sao usados em bruto, tal como vem da API.
fun calcularIad(
  hp: Int,
  attack: Int,
  defense: Int,
  speed: Int,
  height: Int,
  weight: Int,
  tipos: List<String>,
): Double {
  // Sem tipos nao ha calculo possivel.
  if (tipos.isEmpty()) {
    return 0.0
  }

  val fatorCategoria = tipos.sumOf { valorCategoria(it) }
  val multiplicador = multiplicadorTipo(tipos.first())

  val numerador = (hp * fatorCategoria) + (attack * speed)
  // +1 no defense e na height evita divisao por zero.
  val denominador = ((defense + 1) * (height + 1)) + weight

  val resultado = (numerador.toDouble() / denominador) * multiplicador

  // Arredonda a 2 casas decimais.
  return Math.round(resultado * 100.0) / 100.0
}

// Extension que le os dados do Pokemon e devolve o seu IAD.
fun PokemonInfo.indiceMca(): Double {
  // Ordena os tipos por slot antes de extrair o nome.
  val tipos = types.sortedBy { it.slot }.map { it.type.name }
  return calcularIad(hp, attack, defense, speed, height, weight, tipos)
}
