package com.caspercodes.pokemonapi.service;

import com.caspercodes.pokemonapi.dto.PokemonDtoResponse;

import java.util.List;

public interface PokemonService {
    PokemonDtoResponse createPokemon(PokemonDtoResponse pokemonDtoResponse);
    List<PokemonDtoResponse> getAllPokemon();
    PokemonDtoResponse getPokemonById(Long id);
}
