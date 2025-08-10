package com.caspercodes.pokemonapi.repository;

import com.caspercodes.pokemonapi.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
}
