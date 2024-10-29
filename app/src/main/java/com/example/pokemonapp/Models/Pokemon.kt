package com.example.pokemonapp.Models

data class Pokemon(
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<Types>,
    val sprites: Sprites
)

data class Types(val type: TypeDetail)
data class TypeDetail(val name: String)
data class Sprites(val frontDefault: String)

