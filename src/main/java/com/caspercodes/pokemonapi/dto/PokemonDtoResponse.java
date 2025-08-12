package com.caspercodes.pokemonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonDtoResponse {
    private Long id;
    private String name;
    private String type;
}
