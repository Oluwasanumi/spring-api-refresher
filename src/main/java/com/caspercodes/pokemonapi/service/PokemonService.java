package com.caspercodes.pokemonapi.service;

import com.caspercodes.pokemonapi.dto.PokemonDtoResponse;

import java.util.List;

public interface PokemonService {
    PokemonDtoResponse createPokemon(PokemonDtoResponse pokemonDtoResponse);
    List<PokemonDtoResponse> getAllPokemon(int pageNo, int pageSize);
    PokemonDtoResponse getPokemonById(Long id);
    PokemonDtoResponse updatePokemon(PokemonDtoResponse pokemonDtoResponse, Long id);
    void deletePokemon(Long id);
}
