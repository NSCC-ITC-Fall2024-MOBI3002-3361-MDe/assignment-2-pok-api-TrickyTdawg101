package com.example.pokemonapp.retrofit

import com.example.pokemonapp.Models.Pokemon
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Pokemon
}

object RetrofitInstance {
    val api: PokemonService by lazy {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonService::class.java)
    }
}