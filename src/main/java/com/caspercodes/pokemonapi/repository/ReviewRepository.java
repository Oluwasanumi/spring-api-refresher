package com.caspercodes.pokemonapi.repository;

import com.caspercodes.pokemonapi.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPokemonId(Long id);
}
