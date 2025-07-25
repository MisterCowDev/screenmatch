package com.aluracursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEpisode(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Episode") Integer currentEpisode,
        @JsonAlias("imdbRating") String rating,
        @JsonAlias("Released") String releasedDate

) {
}
