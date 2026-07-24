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

import org.junit.Assert.assertEquals
import org.junit.Test

class McaCalculatorTest {

  @Test
  fun bulbasaur_tem_indice_de_4_ponto_39() {
    val iad = calcularIad(45, 49, 49, 45, 7, 69, listOf("grass", "poison"))
    assertEquals(4.39, iad, 0.001)
  }

  @Test
  fun charmander_tem_indice_de_12_ponto_68() {
    val iad = calcularIad(39, 52, 43, 65, 6, 85, listOf("fire"))
    assertEquals(12.68, iad, 0.001)
  }

  @Test
  fun charizard_tem_indice_de_5_ponto_51() {
    val iad = calcularIad(78, 84, 78, 100, 17, 905, listOf("fire", "flying"))
    assertEquals(5.51, iad, 0.001)
  }

  @Test
  fun pikachu_tem_indice_de_26_ponto_64() {
    val iad = calcularIad(35, 55, 40, 90, 4, 60, listOf("electric"))
    assertEquals(26.64, iad, 0.001)
  }

  @Test
  fun onix_tem_indice_de_0_ponto_21() {
    val iad = calcularIad(35, 45, 160, 70, 88, 2100, listOf("rock", "ground"))
    assertEquals(0.21, iad, 0.001)
  }

  @Test
  fun eevee_tem_indice_de_12_ponto_06() {
    val iad = calcularIad(55, 55, 50, 55, 3, 65, listOf("normal"))
    assertEquals(12.06, iad, 0.001)
  }

  @Test
  fun snorlax_tem_indice_de_0_ponto_65() {
    val iad = calcularIad(160, 110, 65, 30, 21, 4600, listOf("normal"))
    assertEquals(0.65, iad, 0.001)
  }

  @Test
  fun dragonite_tem_indice_de_2_ponto_83() {
    val iad = calcularIad(91, 134, 95, 80, 22, 2100, listOf("dragon", "flying"))
    assertEquals(2.83, iad, 0.001)
  }

  @Test
  fun mewtwo_tem_indice_de_4_ponto_77() {
    val iad = calcularIad(106, 110, 90, 130, 20, 1220, listOf("psychic"))
    assertEquals(4.77, iad, 0.001)
  }

  @Test
  fun skarmory_tem_indice_de_2_ponto_18() {
    val iad = calcularIad(65, 80, 140, 70, 17, 505, listOf("steel", "flying"))
    assertEquals(2.18, iad, 0.001)
  }

  @Test
  fun lista_de_tipos_vazia_devolve_zero() {
    val iad = calcularIad(45, 49, 49, 45, 7, 69, emptyList())
    assertEquals(0.0, iad, 0.001)
  }

  @Test
  fun tipo_desconhecido_tem_valor_de_categoria_zero() {
    assertEquals(0, valorCategoria("misterio"))
  }

  @Test
  fun tipo_monotipico_usa_o_proprio_valor_de_categoria() {
    assertEquals(6, valorCategoria("grass"))
    assertEquals(10, valorCategoria("dragon"))
  }

  @Test
  fun tipo_bitipico_soma_os_valores_de_categoria() {
    val fatorBulbasaur = valorCategoria("grass") + valorCategoria("poison")
    assertEquals(12, fatorBulbasaur)
  }

  @Test
  fun multiplicador_de_fire_water_electric_e_1_ponto_35() {
    assertEquals(1.35, multiplicadorTipo("fire"), 0.001)
    assertEquals(1.35, multiplicadorTipo("water"), 0.001)
    assertEquals(1.35, multiplicadorTipo("electric"), 0.001)
  }

  @Test
  fun multiplicador_de_grass_bug_poison_e_0_ponto_75() {
    assertEquals(0.75, multiplicadorTipo("grass"), 0.001)
    assertEquals(0.75, multiplicadorTipo("bug"), 0.001)
    assertEquals(0.75, multiplicadorTipo("poison"), 0.001)
  }

  @Test
  fun multiplicador_de_outro_tipo_e_1_ponto_00() {
    assertEquals(1.00, multiplicadorTipo("normal"), 0.001)
    assertEquals(1.00, multiplicadorTipo("dragon"), 0.001)
  }

  @Test
  fun multiplicador_vem_do_tipo_primario_e_nao_do_secundario() {
    val firePrimario = calcularIad(10, 10, 10, 10, 10, 10, listOf("fire", "flying"))
    val flyingPrimario = calcularIad(10, 10, 10, 10, 10, 10, listOf("flying", "fire"))
    assertEquals(2.47, firePrimario, 0.001)
    assertEquals(1.83, flyingPrimario, 0.001)
  }
}
