package com.caspercodes.pokemonapi.service.implementation;

import com.caspercodes.pokemonapi.dto.PokemonDtoResponse;
import com.caspercodes.pokemonapi.model.Pokemon;
import com.caspercodes.pokemonapi.repository.PokemonRepository;
import com.caspercodes.pokemonapi.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Override
    public PokemonDtoResponse createPokemon(PokemonDtoResponse pokemonDtoResponse) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDtoResponse.getName());
        pokemon.setType(pokemonDtoResponse.getType());

        Pokemon savedPokemon = pokemonRepository.save(pokemon);

        return new PokemonDtoResponse(
                savedPokemon.getId(),
                savedPokemon.getName(),
                savedPokemon.getType()
        );

        /*

        // Here I am using a setter for the DTO response object.
        PokemonDtoResponse response = new PokemonDtoResponse();
        pokemonDtoResponse.setId(savedPokemon.getId());
        pokemonDtoResponse.setName(savedPokemon.getName());
        pokemonDtoResponse.setType(savedPokemon.getType());

        return response;

        */
    }
}
