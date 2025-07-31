package com.aluracursos.screenmatch.service;

import com.aluracursos.screenmatch.dto.EpisodeDTO;
import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repositorioSerie;

    public List<SerieDTO> getAllSeries(){
        return convertirDatos(repositorioSerie.findAll());
    }

    public List<SerieDTO> getTop5Series(){
        return convertirDatos(repositorioSerie.findTop5ByOrderByRatingDesc());
    }

    public List<SerieDTO> getRecentRealeases(){
        return convertirDatos(repositorioSerie.lastestSerieRealease());
    }

    public List<SerieDTO> convertirDatos(List<Serie> serie){
        return serie.stream().map(s -> new SerieDTO(s.getId(),
                s.getTitle(),
                s.getYear(),
                s.getGenre(),
                s.getActors(),
                s.getPoster(),
                s.getPlot(),
                s.getRating(),
                s.getTotalSeason())).collect(Collectors.toList());
    }


    public SerieDTO getForId(Long id) {
        Optional<Serie> serie = repositorioSerie.findById(id);
        if (serie.isPresent()){
            Serie s = serie.get();
            return new SerieDTO(s.getId(),
                    s.getTitle(),
                    s.getYear(),
                    s.getGenre(),
                    s.getActors(),
                    s.getPoster(),
                    s.getPlot(),
                    s.getRating(),
                    s.getTotalSeason());
        }
        return null;
    }

    public List<EpisodeDTO> getAllEpisodesFromSerie(Long id) {
        Optional<Serie> serie = repositorioSerie.findById(id);
        if (serie.isPresent()){
            Serie s = serie.get();
            return s.getEpisodes().stream().map(episode -> new EpisodeDTO(
               episode.getSeason(),
               episode.getTitle(),
               episode.getNumberEpisode())).collect(Collectors.toList());
        }
        return null;
    }
}
