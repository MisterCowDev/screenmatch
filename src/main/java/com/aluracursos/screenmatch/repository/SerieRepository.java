package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.model.Episode;
import com.aluracursos.screenmatch.model.Genre;
import com.aluracursos.screenmatch.model.Serie;
import org.hibernate.annotations.processing.Find;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTitleContainsIgnoreCase(String nombreSerie);

    List<Serie> findTop5ByOrderByRatingDesc();

    List<Serie> findByGenre(Genre genero);

    @Query("SELECT s FROM Serie s WHERE s.totalSeason >= :numberSeason AND s.rating >= :ratingSeason")
    List<Serie> findBySeasonAndRating(Integer numberSeason, Double ratingSeason);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE e.title ILIKE %:serieName%")
    List<Episode> episodeForName(String serieName);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s = :serie ORDER BY e.rating DESC LIMIT 5")
    List<Episode> top5EpisodesForSerie(Serie serie);

    @Query("SELECT s FROM Serie s " + "JOIN s.episodes e " + "GROUP BY s " + "ORDER BY MAX(e.dateReleased) DESC LIMIT 5")
    List<Serie> lastestSerieRealease();
}
