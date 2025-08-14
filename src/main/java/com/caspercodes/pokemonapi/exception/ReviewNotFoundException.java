package com.caspercodes.pokemonapi.exception;

import java.io.Serial;

public class ReviewNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ReviewNotFoundException(String message) {
        super(message);
    }
}
