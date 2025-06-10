package Main;

import model.DataEpisode;
import model.DataSeason;
import model.DataSerie;
import model.Episode;
import service.ConsumoAPI;
import service.DataConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    Scanner lecture = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://omdbapi.com/?t=";
    private final String API_KEY = "&apikey=9ff43673";
    private DataConverter converter = new DataConverter();


    public void showMenu(){
        System.out.print("Escribe el nombre de la serie que quieres buscar: ");
        var serieName = lecture.nextLine();
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

        /*
        // Mostrar todos los episodios de todas las temporadas
        seasons.forEach(t -> t.episodes().forEach(e ->
                System.out.println(
                    "\nSeason: " + t.season() +
                    "\nTítulo: " + e.titulo() +
                        "\nEpisodio: " + e.currentEpisode() +
                        "\nRating: " + e.rating() +
                        "\nFecha de estreno: " + e.releasedDate()
        )));
        */

        // Mostrar los TOP 5 espisodios con mejor calificación
        List<DataEpisode> dataEpisodes = seasons.stream()
                .flatMap(t -> t.episodes().stream())
                .collect(Collectors.toList());
        System.out.println("\nTop 5 espisodios\n");
        dataEpisodes.stream()
                .filter(p -> !p.rating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DataEpisode::rating).reversed())
                .limit(5)
                .forEach(System.out::println);
        System.out.println("");


        // Convertir los datos a una clase tipo Episode
        List<Episode> episodes = seasons.stream()
                .flatMap(t -> t.episodes().stream()
                        .map(d -> new Episode(t.season(),d)))
                .collect(Collectors.toList());
        episodes.forEach(System.out::println);
        System.out.println("");

        // Mostrar la calificación de cada temporada
        Map<Integer, Double> seasonsRating = episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.groupingBy(Episode::getSeason,
                        Collectors.averagingDouble(Episode::getRating)));
        System.out.println(seasonsRating);

        /*
        // Buscar capitulos ingresando un año especifico
        System.out.print("Ingresa una fecha de un episodio (año): ");
        var fecha = lecture.nextInt();
        lecture.nextLine();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate searchedData = LocalDate.of(fecha, 1, 1);
        episodes.stream()
                .filter(e -> e.getDateReleased() != null && e.getDateReleased().isAfter(searchedData))
                .forEach(e -> System.out.println(
                        "Tempodada: " + e.getSeason() +
                                " Episodio: " + e.getNumberEpisode() +
                                " Fecha de lanzamiento: " + e.getDateReleased().format(dtf)
                ));

        // Buscar un episodio por un pedazo del título
        System.out.print("Ingresa un título para la busqueda: ");
        var searchForTitle = lecture.nextLine();
        Optional<Episode> searchEpisode = episodes.stream()
                .filter(t -> t.getTitle().toLowerCase().contains(searchForTitle.toLowerCase()))
                .findFirst();
        if (searchEpisode.isPresent()) {
            System.out.println("\nEpisodio encontrado");
            System.out.println("Título: " + searchEpisode.get().getTitle() +
                    "\nNúmero de episodio: " + searchEpisode.get().getNumberEpisode() +
                    "\nFecha de lanzamiento: " + searchEpisode.get().getDateReleased() +
                    "\nCalificación: " + searchEpisode.get().getRating() +
                    "\nNúmero de temporada: " + searchEpisode.get().getSeason());
        } else {
            System.out.println("Episodio no encontrado");
        }
        */


    }
}
