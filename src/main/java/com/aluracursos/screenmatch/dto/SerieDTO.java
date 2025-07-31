package com.aluracursos.screenmatch.dto;

import com.aluracursos.screenmatch.model.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record SerieDTO(Long id,
                       String title,
    String year,
    Genre genre,
    String actors,
    String poster,
    String plot,
    Double rating,
    Integer totalSeason) {
}
