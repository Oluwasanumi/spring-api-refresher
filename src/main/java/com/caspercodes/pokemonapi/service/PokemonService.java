package com.caspercodes.pokemonapi.service;

import com.caspercodes.pokemonapi.dto.PaginationResponse;
import com.caspercodes.pokemonapi.dto.PokemonDtoResponse;

public interface PokemonService {
    PokemonDtoResponse createPokemon(PokemonDtoResponse pokemonDtoResponse);
    PaginationResponse getAllPokemon(int pageNo, int pageSize);
    PokemonDtoResponse getPokemonById(Long id);
    PokemonDtoResponse updatePokemon(PokemonDtoResponse pokemonDtoResponse, Long id);
    void deletePokemon(Long id);
}
