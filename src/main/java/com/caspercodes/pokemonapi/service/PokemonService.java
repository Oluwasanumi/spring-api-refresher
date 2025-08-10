package com.caspercodes.pokemonapi.service;

import com.caspercodes.pokemonapi.dto.PokemonDtoResponse;

public interface PokemonService {
    PokemonDtoResponse createPokemon(PokemonDtoResponse pokemonDtoResponse);
}
