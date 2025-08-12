package com.caspercodes.pokemonapi.controller;

import com.caspercodes.pokemonapi.dto.PokemonDtoResponse;
import com.caspercodes.pokemonapi.model.Pokemon;
import com.caspercodes.pokemonapi.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("pokemon")
    public ResponseEntity<List<PokemonDtoResponse>> getAllPokemon() {
        return new ResponseEntity<>(pokemonService.getAllPokemon(), HttpStatus.OK);
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
    public ResponseEntity<Pokemon> updatePokemon(@RequestBody Pokemon pokemon, @PathVariable("id") Long pokemonId) {
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getType());
        return ResponseEntity.ok(pokemon);
    }

    @DeleteMapping("/pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") int pokemonId) {
        System.out.println(pokemonId);
        return ResponseEntity.ok("Successfully deleted pokemon with ID: " + pokemonId);
    }
}