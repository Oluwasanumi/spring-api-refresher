package com.caspercodes.pokemonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDtoResponse {
    private Long id;
    private String title;
    private String content;
    private int stars;
}
