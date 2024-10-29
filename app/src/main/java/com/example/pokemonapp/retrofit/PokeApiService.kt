package com.example.pokemonapp.retrofit

import com.example.pokemonapp.Models.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Pokemon
}
