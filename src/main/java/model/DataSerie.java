package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Ignorar campos no mapeados
@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSerie(
        @JsonAlias("Title") String title,
        @JsonAlias("Year") String year,
        @JsonAlias("imdbRating") String rating,
        @JsonAlias("totalSeasons") Integer totalSeason
        ) {

}
