package com.aluracursos.screenmatch.model;

import jakarta.persistence.*;

import java.security.spec.ECPoint;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "episodios")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer season;
    private String title;
    private Integer numberEpisode;
    private Double rating;
    private LocalDate dateReleased;
    @ManyToOne()
    private Serie serie;

    public Episode(){

    }

    public Episode(Integer season, DataEpisode d) {
        this.season = season;
        this.title = d.titulo();
        this.numberEpisode = d.currentEpisode();
        try {
            this.rating = Double.valueOf(d.rating());
        } catch (NumberFormatException e) {
            this.rating = 0.0;
        }
        try {
            this.dateReleased = LocalDate.parse(d.releasedDate());
        } catch (DateTimeParseException e) {
            this.dateReleased = null;
        }

    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumberEpisode() {
        return numberEpisode;
    }

    public void setNumberEpisode(Integer numberEpisode) {
        this.numberEpisode = numberEpisode;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(LocalDate dateReleased) {
        this.dateReleased = dateReleased;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "season=" + season +
                ", title='" + title + '\'' +
                ", numberEpisode=" + numberEpisode +
                ", rating=" + rating +
                ", dateReleased=" + dateReleased +
                '}';
    }
}
