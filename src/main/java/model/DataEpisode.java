package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEpisode(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Episode") Integer currentEpisode,
        @JsonAlias("imdbRating") Double rating,
        @JsonAlias("Released") String releasedDate

) {
}
