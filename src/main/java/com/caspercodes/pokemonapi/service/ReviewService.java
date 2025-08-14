package com.caspercodes.pokemonapi.service;

import com.caspercodes.pokemonapi.dto.ReviewDtoResponse;

import java.util.List;

public interface ReviewService {
    ReviewDtoResponse createReview(Long pokemonId, ReviewDtoResponse reviewDtoResponse);
    List<ReviewDtoResponse> getReviewByPokemonId(Long id);
    ReviewDtoResponse getReviewById(Long reviewId, Long pokemonId);
    ReviewDtoResponse updateReview(Long pokemonId, Long reviewId, ReviewDtoResponse reviewDtoResponse);
    void deleteReview(Long pokemonId, Long reviewId);

}
