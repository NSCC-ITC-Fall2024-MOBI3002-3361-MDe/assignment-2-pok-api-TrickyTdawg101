package com.example.pokemonapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pokemonapp.R
import com.example.pokemonapp.Models.PokemonViewModel

@Composable
fun PokemonSearchScreen(pokeViewModel: PokemonViewModel = viewModel()){

    Scaffold (
        topBar = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                // Center the content within the Box
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), //loads image resource
                    contentDescription = "the pokemon logo", //description
                    modifier = Modifier
                        .size(200.dp)
                        .padding(5.dp)
                )
            }
        },
        content = {paddingValues ->
            var pokemonSearch by remember { mutableStateOf("") }
            val pokemon by pokeViewModel.pokemon.collectAsState()
            Row(modifier = Modifier.fillMaxWidth().padding(paddingValues)) {
                TextField(
                    value = pokemonSearch,
                    onValueChange = {pokemonSearch = it},
                    singleLine = true,
                    modifier = Modifier.padding(8.dp)
                )
                Button(onClick = {pokeViewModel.fetchPokemon(pokemonSearch)}) { Text("Go") }
            }


            Column (modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                pokemon?.let {
                    Spacer(modifier = Modifier.height(100.dp))
                    AsyncImage(
                        model = it.sprites.front_default,
                        contentDescription = "${it.name} sprite",
                        modifier = Modifier.size(128.dp)
                    )
                    Text("Name: ${it.name}")
                    Text("Height: ${it.height}")
                    Text("Weight: ${it.weight}")
                    Text("Type: ${it.types.joinToString { type -> type.type.name }}")
                }
            }
        }
    )
}


