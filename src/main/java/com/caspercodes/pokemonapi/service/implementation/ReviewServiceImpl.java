package com.caspercodes.pokemonapi.service.implementation;

import com.caspercodes.pokemonapi.dto.ReviewDtoResponse;
import com.caspercodes.pokemonapi.exception.PokemonNotFoundException;
import com.caspercodes.pokemonapi.exception.ReviewNotFoundException;
import com.caspercodes.pokemonapi.model.Pokemon;
import com.caspercodes.pokemonapi.model.Review;
import com.caspercodes.pokemonapi.repository.PokemonRepository;
import com.caspercodes.pokemonapi.repository.ReviewRepository;
import com.caspercodes.pokemonapi.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final PokemonRepository pokemonRepository;

    @Override
    public ReviewDtoResponse createReview(Long pokemonId, ReviewDtoResponse reviewDtoResponse) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = mapToEntity(reviewDtoResponse);
        review.setPokemon(pokemon);
        Review savedReview = reviewRepository.save(review);
        return mapToDto(savedReview);
    }

    @Override
    public List<ReviewDtoResponse> getReviewByPokemonId(Long id) {
        List<Review> reviews = reviewRepository.findByPokemonId(id);
        return reviews.stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public ReviewDtoResponse getReviewById(Long pokemonId, Long reviewId) {
        Review review = validateAndGetReview(pokemonId, reviewId);
        return mapToDto(review);
    }

    @Override
    public ReviewDtoResponse updateReview(Long pokemonId, Long reviewId, ReviewDtoResponse reviewDtoResponse) {
        Review review = validateAndGetReview(pokemonId, reviewId);
        updateReviewMapper(review, reviewDtoResponse);
        Review updatedReview = reviewRepository.save(review);
        return mapToDto(updatedReview);
    }

    @Override
    public void deleteReview(Long pokemonId, Long reviewId) {
        validateAndGetReview(pokemonId, reviewId);
        reviewRepository.deleteById(reviewId);
    }

    private Review validateAndGetReview(Long pokemonId, Long reviewId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review associated with pokemon not found"));

        if (!Objects.equals(review.getPokemon().getId(), pokemon.getId())) {
            throw new ReviewNotFoundException("This review does not belong to the specified pokemon");
        }
        return review;
    }

    private ReviewDtoResponse mapToDto(Review review) {
        return new ReviewDtoResponse(
                review.getId(),
                review.getTitle(),
                review.getContent(),
                review.getStars()
        );
    }

    private Review mapToEntity(ReviewDtoResponse reviewDtoResponse) {
        Review review = new Review();
        review.setTitle(reviewDtoResponse.getTitle());
        review.setContent(reviewDtoResponse.getContent());
        review.setStars(reviewDtoResponse.getStars());
        return review;
    }

    private void updateReviewMapper(Review review, ReviewDtoResponse reviewDtoResponse) {
        review.setTitle(reviewDtoResponse.getTitle());
        review.setContent(reviewDtoResponse.getContent());
        review.setStars(reviewDtoResponse.getStars());
    }
}