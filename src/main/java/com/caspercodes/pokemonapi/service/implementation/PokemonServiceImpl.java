package com.caspercodes.pokemonapi.service.implementation;

import com.caspercodes.pokemonapi.dto.PaginationResponse;
import com.caspercodes.pokemonapi.dto.PokemonDtoResponse;
import com.caspercodes.pokemonapi.exception.PokemonNotFoundException;
import com.caspercodes.pokemonapi.model.Pokemon;
import com.caspercodes.pokemonapi.repository.PokemonRepository;
import com.caspercodes.pokemonapi.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Override
    public PokemonDtoResponse createPokemon(PokemonDtoResponse pokemonDtoResponse) {
        Pokemon pokemon = mapToEntity(pokemonDtoResponse);
        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        return mapToDto(savedPokemon);
    }

    // .stream().map() is basically for many items, it converts a list of entities to a list of DTOs.
    @Override
    public PaginationResponse getAllPokemon(int pageNo, int pageSize) {
        Page<Pokemon> allPokemon = pokemonRepository.findAll(PageRequest.of(pageNo, pageSize));
        return mapToPaginationResponse(allPokemon);
    }

    // .map() is for a single item, it converts a single entity to a DTO.
    @Override
    public PokemonDtoResponse getPokemonById(Long id) {
        return pokemonRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon not found with id: " + id));
    }

    @Override
    public PokemonDtoResponse updatePokemon(PokemonDtoResponse pokemonDtoResponse, Long id) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon to be updated of id: " + id + " cannot found"));

        pokemon.setName(pokemonDtoResponse.getName());
        pokemon.setType(pokemonDtoResponse.getType());
        Pokemon updatedPokemon = pokemonRepository.save(pokemon);

        return mapToDto(updatedPokemon);
    }

    @Override
    public void deletePokemon(Long id) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon to be deleted with id: " + id + " cannot found"));
        pokemonRepository.delete(pokemon);
    }

    private PokemonDtoResponse mapToDto(Pokemon pokemon) {
        return new PokemonDtoResponse(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getType()
        );
    }
    
    private Pokemon mapToEntity(PokemonDtoResponse pokemonDtoResponse) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDtoResponse.getName());
        pokemon.setType(pokemonDtoResponse.getType());
        return pokemon;
    }

    private PaginationResponse mapToPaginationResponse(Page<Pokemon> pokemonPage) {
        List<PokemonDtoResponse> content = pokemonPage.getContent().stream()
                .map(this::mapToDto)
                .toList();

        return new PaginationResponse(
                content,
                pokemonPage.getNumber(),
                pokemonPage.getSize(),
                pokemonPage.getTotalElements(),
                pokemonPage.getTotalPages(),
                pokemonPage.isLast()
        );
    }
}