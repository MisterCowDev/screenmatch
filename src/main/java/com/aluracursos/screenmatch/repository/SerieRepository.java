package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.model.Genre;
import com.aluracursos.screenmatch.model.Serie;
import org.hibernate.annotations.processing.Find;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTitleContainsIgnoreCase(String nombreSerie);

    List<Serie> findTop5ByOrderByRatingDesc();

    List<Serie> findByGenre(Genre genero);
}
