package com.caspercodes.pokemonapi.service.implementation;

import com.caspercodes.pokemonapi.dto.PokemonDtoResponse;
import com.caspercodes.pokemonapi.model.Pokemon;
import com.caspercodes.pokemonapi.repository.PokemonRepository;
import com.caspercodes.pokemonapi.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

        // Here I'm using a constructor for the DTO response object. I prefer this, easier to understand and maintain.
        return new PokemonDtoResponse(
                savedPokemon.getId(),
                savedPokemon.getName(),
                savedPokemon.getType()
        );
    }

    @Override
    public List<PokemonDtoResponse> getAllPokemon() {
        List<Pokemon> allPokemon = pokemonRepository.findAll();
        return allPokemon.stream().map(this::mapToDto).toList();
    }

    //Custom mapper, entity to DTO
    private PokemonDtoResponse mapToDto(Pokemon pokemon) {
        PokemonDtoResponse pokemonDtoResponse = new PokemonDtoResponse();
        pokemonDtoResponse.setId(pokemon.getId());
        pokemonDtoResponse.setName(pokemon.getName());
        pokemonDtoResponse.setType(pokemon.getType());
        return pokemonDtoResponse;
    }

    //Custom mapper, DTO to entity
    private Pokemon mapToEntity(PokemonDtoResponse pokemonDtoResponse) {
        Pokemon pokemon = new Pokemon();
//        pokemon.setId(pokemonDtoResponse.getId());
        pokemon.setName(pokemonDtoResponse.getName());
        pokemon.setType(pokemonDtoResponse.getType());
        return pokemon;
    }
}
