package com.example.pokemonapp.Models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val _pokemon = MutableStateFlow<Pokemon?>(null)
    val pokemon: StateFlow<Pokemon?> = _pokemon

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchPokemon(name: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.fetchPokemon(name)
                _pokemon.value = response
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to fetch Pokémon: ${e.message}"
            }
        }
    }
}

