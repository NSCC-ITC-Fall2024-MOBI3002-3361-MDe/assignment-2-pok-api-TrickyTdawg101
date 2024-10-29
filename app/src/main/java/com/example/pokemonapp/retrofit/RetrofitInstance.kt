package com.example.pokemonapp.retrofit

import com.example.pokemonapp.Models.Pokemon
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Retrofit API interface
interface PokemonApi {
    @GET("api/v2/pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Pokemon
}

// Singleton object for Retrofit instance
object RetrofitInstance {
    private const val BASE_URL = "https://pokeapi.co/"

    val api: PokemonApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }
}
