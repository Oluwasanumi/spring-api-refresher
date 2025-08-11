package com.caspercodes.pokemonapi.exception;

import java.io.Serial;

public class PokemonNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public PokemonNotFoundException(String message) {
        super(message);
    }
}
