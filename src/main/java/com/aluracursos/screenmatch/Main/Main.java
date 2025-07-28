package com.aluracursos.screenmatch.Main;

import com.aluracursos.screenmatch.model.*;
import com.aluracursos.screenmatch.repository.SerieRepository;
import com.aluracursos.screenmatch.service.SerieApiClient;
import com.aluracursos.screenmatch.service.DataConverter;

import java.security.spec.ECPoint;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    Scanner lecture = new Scanner(System.in);
    private SerieApiClient serieApiClient = new SerieApiClient();
    private final String URL_BASE = "https://omdbapi.com/?t=";
    private final String API_KEY = "&apikey=9ff43673";
    private DataConverter converter = new DataConverter();
    private List<DataSerie> dataSeries = new ArrayList<>();
    private SerieRepository repositorio;
    private List<Serie> series;

    public Main(SerieRepository repository) {
        this.repositorio = repository;
    }

    public void showMenu(){
        var option = -1;
        while (option != 0){
            var menuOption = """
                    
                    ========================
                    1 - Buscar datos de una serie (Agregar en base de datos)
                    2 - Buscar los episodios de una serie (Agregar sus episodios)
                    3 - Mostrar series buscadas
                    4 - Buscar una serie en la base de datos
                    5 - Buscar el TOP 5 de series
                    6 - Buscar una serie por su género
                    0 - Salir de la aplicación
                    ========================
                    """;
            System.out.print(menuOption + "Opción: ");
            option = lecture.nextInt();
            lecture.nextLine();

            switch (option){
                case 0:
                    System.out.println("Has salido de la aplicación");
                    break;
                case 1:
                    searchSeason();
                    break;
                case 2:
                    searchEpisodeForSeason();
                case 3:
                    showSearchSeries();
                    break;
                case 4:
                    showSerieForTitle();
                    break;
                case 5:
                    showTop5Series();
                    break;
                case 6:
                    searchSerieForGenre();
                    break;
                default:
                    System.out.println("Opción ingresada no es valida");

            }
        }

//        System.out.print("Escribe el nombre de la serie que quieres buscar: ");
//        var serieName = lecture.nextLine();
//        var json = serieApiClient.getData(URL_BASE + serieName.replace(" ", "+").toLowerCase() + API_KEY);
//        var data = converter.getData(json, DataSerie.class);
//        System.out.println("\nTítulo: " + data.title() +
//                "\nAño: " + data.year() +
//                "\nRating: " + data.rating() +
//                "\nTemporadas: " + data.totalSeason());
//
//        List<DataSeason> seasons = new ArrayList<>();
//        for (int i = 1; i <= data.totalSeason(); i++) {
//            var jsonSeasons = serieApiClient.getData(URL_BASE + serieName.replace(" ", "+").toLowerCase() + "&Season=" + i + API_KEY);
//            var dataSeason = converter.getData(jsonSeasons, DataSeason.class);
//            seasons.add(dataSeason);
//        }
//
//        /*
//        // Mostrar todos los episodios de todas las temporadas
//        seasons.forEach(t -> t.episodes().forEach(e ->
//                System.out.println(
//                    "\nSeason: " + t.season() +
//                    "\nTítulo: " + e.titulo() +
//                        "\nEpisodio: " + e.currentEpisode() +
//                        "\nRating: " + e.rating() +
//                        "\nFecha de estreno: " + e.releasedDate()
//        )));
//        */
//
//        // Mostrar los TOP 5 espisodios con mejor calificación
//        List<DataEpisode> dataEpisodes = seasons.stream()
//                .flatMap(t -> t.episodes().stream())
//                .collect(Collectors.toList());
//        System.out.println("\nTop 5 espisodios\n");
//        dataEpisodes.stream()
//                .filter(p -> !p.rating().equalsIgnoreCase("N/A"))
//                .sorted(Comparator.comparing(DataEpisode::rating).reversed())
//                .limit(5)
//                .forEach(System.out::println);
//        System.out.println("");
//
//
//        // Convertir los datos a una clase tipo Episode
//        List<Episode> episodes = seasons.stream()
//                .flatMap(t -> t.episodes().stream()
//                        .map(d -> new Episode(t.season(),d)))
//                .collect(Collectors.toList());
//        episodes.forEach(System.out::println);
//        System.out.println("");
//
//        // Mostrar la calificación de cada temporada
//        Map<Integer, Double> seasonsRating = episodes.stream()
//                .filter(e -> e.getRating() > 0.0)
//                .collect(Collectors.groupingBy(Episode::getSeason,
//                        Collectors.averagingDouble(Episode::getRating)));
//        System.out.println(seasonsRating);
//
//        /*
//        // Buscar capitulos ingresando un año especifico
//        System.out.print("Ingresa una fecha de un episodio (año): ");
//        var fecha = lecture.nextInt();
//        lecture.nextLine();
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate searchedData = LocalDate.of(fecha, 1, 1);
//        episodes.stream()
//                .filter(e -> e.getDateReleased() != null && e.getDateReleased().isAfter(searchedData))
//                .forEach(e -> System.out.println(
//                        "Tempodada: " + e.getSeason() +
//                                " Episodio: " + e.getNumberEpisode() +
//                                " Fecha de lanzamiento: " + e.getDateReleased().format(dtf)
//                ));
//
//        // Buscar un episodio por un pedazo del título
//        System.out.print("Ingresa un título para la busqueda: ");
//        var searchForTitle = lecture.nextLine();
//        Optional<Episode> searchEpisode = episodes.stream()
//                .filter(t -> t.getTitle().toLowerCase().contains(searchForTitle.toLowerCase()))
//                .findFirst();
//        if (searchEpisode.isPresent()) {
//            System.out.println("\nEpisodio encontrado");
//            System.out.println("Título: " + searchEpisode.get().getTitle() +
//                    "\nNúmero de episodio: " + searchEpisode.get().getNumberEpisode() +
//                    "\nFecha de lanzamiento: " + searchEpisode.get().getDateReleased() +
//                    "\nCalificación: " + searchEpisode.get().getRating() +
//                    "\nNúmero de temporada: " + searchEpisode.get().getSeason());
//        } else {
//            System.out.println("Episodio no encontrado");
//        }
//        */
    }


    private DataSerie getDataSerie(){
        System.out.print("Ingresa el nombre de la serie que deseas buscar: ");
        String serieName = lecture.nextLine();
        var jsonSerie = serieApiClient.getData(URL_BASE + serieName.toLowerCase().replace(" ", "+") + API_KEY);
        DataSerie dataSerie = converter.getData(jsonSerie, DataSerie.class);
        return dataSerie;
    }

    private void searchSeason(){
        DataSerie dataSerie = getDataSerie();
        // dataSeries.add(dataSerie);
        Serie serie = new Serie(dataSerie);
        repositorio.save(serie);
    }

    private void searchEpisodeForSeason(){
        showSearchSeries();
        System.out.print("Escribe el nombre de la serie para ver sus episodios: ");
        var serieName = lecture.nextLine();

        Optional<Serie> serie = series.stream().filter(s -> s.getTitle().toLowerCase().contains(serieName.toLowerCase()))
                .findFirst();
        if (serie.isPresent()){
            var foundSerie = serie.get();
            List<DataSeason> seasons = new ArrayList<>();
            for (int i = 1; i <= foundSerie.getTotalSeason(); i++) {
                var jsonSeason = serieApiClient.getData(URL_BASE + foundSerie.getTitle().toLowerCase().replace(" ", "+") + "&season=" + i + API_KEY);
                DataSeason dataSeason = converter.getData(jsonSeason, DataSeason.class);
                seasons.add(dataSeason);
            }
            seasons.forEach(System.out::println);

            List<Episode> episodes = seasons.stream()
                    .flatMap(dataSeason -> dataSeason.episodes().stream()
                            .map(dataEpisode -> new Episode(dataEpisode.currentEpisode(), dataEpisode)))
                    .collect(Collectors.toList());
            foundSerie.setEpisodes(episodes);
            repositorio.save(foundSerie);
        }



        /*DataSerie dataSerie = getDataSerie();
        List<DataSeason> seasons = new ArrayList<>();
        for (int i = 1; i <= dataSerie.totalSeason(); i++) {
            var jsonSeason = serieApiClient.getData(URL_BASE + dataSerie.title().toLowerCase().replace(" ", "+") + "&season=" + i + API_KEY);
            DataSeason dataSeason = converter.getData(jsonSeason, DataSeason.class);
            seasons.add(dataSeason);
        }
        seasons.forEach(dataSeason -> dataSeason.episodes()
                .forEach(dataEpisode ->
                        System.out.println(
                                "Temporada: " + dataSeason.season() +
                                        "\tEpisodio: " + dataEpisode.currentEpisode() +
                                        "\tTítulo: " + dataEpisode.titulo()
                        )));
         */
    }

    private void showSearchSeries(){
        /*
        List<Serie> series = new ArrayList<>();
        series = dataSeries.stream()
                .map(d -> new Serie(d))
                .collect(Collectors.toList());
        series.stream()
                .sorted(Comparator.comparing(Serie::getRating).reversed())
                .forEach(System.out::println);
         */
        series = repositorio.findAll();
        for (Serie serie : series) {
            System.out.println(serie);
        }
    }

    private void showSerieForTitle(){
        System.out.print("Ingresa el título de la serie que buscas: ");
        var serieName = lecture.nextLine();
        Optional<Serie> searchedSerie = repositorio.findByTitleContainsIgnoreCase(serieName);
        if (searchedSerie.isPresent()) {
            System.out.println("La serie buscada es: " + searchedSerie);
        } else {
            System.out.println("La serie no fue encontrada en la base de datos");
        }
    }

    private void showTop5Series(){
        List<Serie> topSeries = repositorio.findTop5ByOrderByRatingDesc();
        topSeries.forEach(s -> System.out.println("Título: " + s.getTitle() + " - Evaluación: " + s.getRating()));
    }

    private void searchSerieForGenre(){
        System.out.print("Ingresa el género de las series que buscas: ");
        var serieGenre = lecture.nextLine();
        var categoria = Genre.fromSpanish(serieGenre);
        List<Serie> serieGenreSearched = repositorio.findByGenre(categoria);
        System.out.println("Las series encontradas del género: " + serieGenre);
        serieGenreSearched.forEach(s -> System.out.println("Título: " + s.getTitle() + " - Género: " + s.getGenre()));
    }
}
