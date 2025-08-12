package com.caspercodes.pokemonapi.dto;

import java.util.List;

public class PaginationResponse {
    private List<PokemonDtoResponse> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;
}
