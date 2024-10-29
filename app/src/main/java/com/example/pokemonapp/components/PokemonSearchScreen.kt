package com.example.pokemonapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pokemonapp.Models.PokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonSearchScreen(pokemonViewModel: PokemonViewModel = viewModel()) {
    var searchQuery by remember { mutableStateOf("") }  // Change to String
    val pokemon by pokemonViewModel.pokemon.collectAsState()
    val errorMessage by pokemonViewModel.error.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pokémon Lookup") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Enter Pokémon Name") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    pokemonViewModel.fetchPokemon(searchQuery)  // Use searchQuery directly
                })
            )

            Button(onClick = { pokemonViewModel.fetchPokemon(searchQuery) }) {
                Text("Go")
            }

            errorMessage?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            pokemon?.let {
                Spacer(modifier = Modifier.height(16.dp))
                AsyncImage(
                    model = it.sprites.front_default,
                    contentDescription = "${it.name} sprite",
                    modifier = Modifier.size(128.dp)
                )
                Text("Name: ${it.name.capitalize()}")
                Text("Height: ${it.height}")
                Text("Weight: ${it.weight}")
                Text("Type: ${it.types.joinToString { type -> type.type.name.capitalize() }}")
            }
        }
    }
}
