package model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Optional;
import java.util.OptionalDouble;

public class Serie {

    private String title;
    private String year;
    private Genre genre;
    private String actors;
    private String poster;
    private String plot;
    private Double rating;
    private Integer totalSeason;

    public Serie(DataSerie dataSerie) {
        this.title = dataSerie.title();
        this.year = dataSerie.year();
        this.genre = Genre.fromString(dataSerie.genre().split(",")[0]);
        this.actors = dataSerie.actors();
        this.poster = dataSerie.poster();
        this.plot = dataSerie.plot();
        this.rating = OptionalDouble.of(Double.valueOf(dataSerie.rating())).orElse(0);
        this.totalSeason = dataSerie.totalSeason();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getTotalSeason() {
        return totalSeason;
    }

    public void setTotalSeason(Integer totalSeason) {
        this.totalSeason = totalSeason;
    }

    @Override
    public String toString() {
        return
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", genre=" + genre +
                ", actors='" + actors + '\'' +
                ", poster='" + poster + '\'' +
                ", plot='" + plot + '\'' +
                ", rating=" + rating +
                ", totalSeason=" + totalSeason +
                '}';
    }
}
