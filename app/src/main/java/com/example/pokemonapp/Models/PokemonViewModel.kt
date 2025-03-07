package com.example.pokemonapp.Models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {
    private val _pokemon = MutableStateFlow<Pokemon?>(null)
    val pokemon: StateFlow<Pokemon?> = _pokemon

    fun fetchPokemon(name: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPokemon(name.lowercase())
                _pokemon.value = response
            } catch(e: Exception){
                //handle the error
            }
        }
    }
}
