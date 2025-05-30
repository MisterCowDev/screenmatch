package com.aluracursos.screenmatch;

import model.DataEpisode;
import model.DataSerie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.ConsumoAPI;
import service.DataConverter;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args)  {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoAPI();
		var json = consumoApi.getData("https://omdbapi.com/?t=prison+break&apikey=9ff43673");
		var jsonEpisodes = consumoApi.getData("https://omdbapi.com/?t=prison+break&Season=1&episode=1&apikey=9ff43673");

		// Obtener datos de una serie especifica
		DataConverter converter = new DataConverter();
		var data = converter.getData(json, DataSerie.class);
		var dataEpisode = converter.getData(jsonEpisodes, DataEpisode.class);
		System.out.println(data);
		System.out.println(dataEpisode);
	}
}
