package com.aluracursos.screenmatch.controller;

import com.aluracursos.screenmatch.dto.EpisodeDTO;
import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.model.Episode;
import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repository.SerieRepository;
import com.aluracursos.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SerieService serieService;

    @GetMapping()
    public List<SerieDTO> getAllSeries(){
        return serieService.getAllSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> getTop5Series(){
        return serieService.getTop5Series();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> getRecentRealeases(){
        return serieService.getRecentRealeases();
    }

    @GetMapping("/{id}")
    public SerieDTO getForId(@PathVariable Long id){
        return serieService.getForId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodeDTO> getAllEpisodesFromSerie(@PathVariable Long id){
        return serieService.getAllEpisodesFromSerie(id);
    }
}
