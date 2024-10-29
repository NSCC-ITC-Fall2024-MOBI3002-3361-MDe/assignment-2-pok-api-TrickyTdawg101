package com.example.pokemonapp.Models

data class Pokemon(
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<TypeWrapper>,
    val sprites: Sprites
)

data class TypeWrapper(val type: Type)
data class Type(val name: String)
data class Sprites(val front_default: String)
