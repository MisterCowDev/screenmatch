package Main;

import model.DataSeason;
import model.DataSerie;
import service.ConsumoAPI;
import service.DataConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    Scanner lecture = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://omdbapi.com/?t=";
    private final String API_KEY = "&apikey=9ff43673";
    private DataConverter converter = new DataConverter();


    public void showMenu(){
        System.out.print("Escribe el nombre de la serie que quieres buscar: ");
        var serieName = lecture.nextLine();
        //var serieName = "prison break";
        var json = consumoAPI.getData(URL_BASE + serieName.replace(" ", "+").toLowerCase() + API_KEY);
        var data = converter.getData(json, DataSerie.class);
        System.out.println("\nTítulo: " + data.title() +
                "\nAño: " + data.year() +
                "\nRating: " + data.rating() +
                "\nTemporadas: " + data.totalSeason());


        List<DataSeason> seasons = new ArrayList<>();
        for (int i = 1; i <= data.totalSeason(); i++) {
            json = consumoAPI.getData(URL_BASE + serieName.replace(" ", "+").toLowerCase() + "&Season=" + i + API_KEY);
            var dataSeason = converter.getData(json, DataSeason.class);
            seasons.add(dataSeason);
        }

        seasons.forEach(t -> t.episodes().forEach(e ->
                System.out.println(
                    "\nSeason: " + t.season() +
                    "\nTítulo: " + e.titulo() +
                        "\nEpisodio: " + e.currentEpisode() +
                        "\nRating: " + e.rating() +
                        "\nFecha de estreno: " + e.releasedDate()
        )));

    }
}
