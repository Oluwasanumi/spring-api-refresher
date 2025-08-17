package com.caspercodes.pokemonapi.controller;

import com.caspercodes.pokemonapi.dto.ReviewDtoResponse;
import com.caspercodes.pokemonapi.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("pokemon/{pokemonId}/reviews")
    public ResponseEntity<ReviewDtoResponse> createReview(@PathVariable(value = "pokemonId") Long pokemonId,
                                                          @RequestBody ReviewDtoResponse reviewDtoResponse) {
        return new ResponseEntity<>(reviewService.createReview(pokemonId, reviewDtoResponse), HttpStatus.CREATED);
    }

    @GetMapping("pokemon/{pokemonId}/reviews")
    public ResponseEntity<List<ReviewDtoResponse>> getReviewsByPokemonId(@PathVariable(value = "pokemonId") Long pokemonId) {
        return new ResponseEntity<>(reviewService.getReviewByPokemonId(pokemonId), HttpStatus.OK);
    }

    @GetMapping("pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDtoResponse> getReviewsById(@PathVariable(value = "pokemonId") Long pokemonId,
                                                            @PathVariable(value = "reviewId") Long reviewId) {
        return new ResponseEntity<>(reviewService.getReviewById(pokemonId, reviewId), HttpStatus.OK);
    }

    @PutMapping("pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDtoResponse> updateReview(@PathVariable(value = "pokemonId") Long pokemonId,
                                                          @PathVariable(value = "reviewId") Long reviewId,
                                                          @RequestBody ReviewDtoResponse reviewDtoResponse) {
        return new ResponseEntity<>(reviewService.updateReview(pokemonId, reviewId, reviewDtoResponse), HttpStatus.OK);
    }

    @DeleteMapping("pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "pokemonId") Long pokemonId,
                                               @PathVariable(value = "reviewId") Long reviewId) {
        reviewService.deleteReview(pokemonId, reviewId);
        return new ResponseEntity<>("Review with id: " + reviewId + " for Pokemon with id: " + pokemonId + " has been deleted successfully", HttpStatus.OK);
    }
}
