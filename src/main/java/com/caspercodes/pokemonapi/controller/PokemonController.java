package com.caspercodes.pokemonapi.controller;

import com.caspercodes.pokemonapi.dto.PaginationResponse;
import com.caspercodes.pokemonapi.dto.PokemonDtoResponse;
import com.caspercodes.pokemonapi.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("pokemon")
    public ResponseEntity<PaginationResponse> getAllPokemon(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(pokemonService.getAllPokemon(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDtoResponse> getPokemonById(@PathVariable Long id) {
        return new ResponseEntity<>(pokemonService.getPokemonById(id), HttpStatus.OK);
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDtoResponse> createPokemon(@RequestBody PokemonDtoResponse pokemonDtoResponse) {
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDtoResponse), HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonDtoResponse> updatePokemon(@RequestBody PokemonDtoResponse pokemonDtoResponse, @PathVariable("id") Long pokemonId) {
        PokemonDtoResponse updatedPokemon = pokemonService.updatePokemon(pokemonDtoResponse, pokemonId);
        return new ResponseEntity<>(updatedPokemon, HttpStatus.OK);
    }

    @DeleteMapping("/pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") Long pokemonId) {
        pokemonService.deletePokemon(pokemonId);
        return new ResponseEntity<>("Pokemon with id: " + pokemonId + " has been deleted successfully", HttpStatus.OK);
    }
}