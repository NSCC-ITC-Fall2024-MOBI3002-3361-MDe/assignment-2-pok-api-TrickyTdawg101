package com.example.pokemonapp.retrofit

import com.example.pokemonapp.Models.Pokemon
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Retrofit API interface
interface PokemonApi {
    @GET("{name}")
    suspend fun fetchPokemon(@Path("name") name: String): Pokemon
}

// Singleton object for Retrofit instance
object RetrofitInstance {

    val api: PokemonApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }
}