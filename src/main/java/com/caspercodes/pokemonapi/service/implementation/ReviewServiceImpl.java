package com.caspercodes.pokemonapi.service.implementation;

import com.caspercodes.pokemonapi.dto.ReviewDtoResponse;
import com.caspercodes.pokemonapi.model.Review;
import com.caspercodes.pokemonapi.repository.ReviewRepository;
import com.caspercodes.pokemonapi.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewDtoResponse createReview(Long pokemonId, ReviewDtoResponse reviewDtoResponse) {
        Review review = new Review();

        review.setTitle(reviewDtoResponse.getTitle());
        review.setContent(reviewDtoResponse.getContent());
        review.setStars(reviewDtoResponse.getStars());

        Review savedReview = reviewRepository.save(review);

        return new ReviewDtoResponse(
                savedReview.getId(),
                savedReview.getTitle(),
                savedReview.getContent(),
                savedReview.getStars()
        );
    }

    @Override
    public List<ReviewDtoResponse> getReviewByPokemonId(Long id) {
        return List.of();
    }

    @Override
    public ReviewDtoResponse getReviewById(Long reviewId, Long pokemonId) {
        return null;
    }

    @Override
    public ReviewDtoResponse updateReview(Long pokemonId, Long reviewId, ReviewDtoResponse reviewDtoResponse) {
        return null;
    }

    @Override
    public void deleteReview(Long pokemonId, Long reviewId) {

    }
}
