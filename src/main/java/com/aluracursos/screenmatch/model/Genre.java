package com.aluracursos.screenmatch.model;

public enum Genre {
    ACCION("Action", "Acción"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comedia"),
    DRAMA("Drama", "Drama"),
    CRIMEN("Crime", "Crimen");

    private String genreOmdb;
    private String generoSpanish;

    Genre(String genreOmdb, String generoSpanish){
        this.genreOmdb = genreOmdb;
        this.generoSpanish = generoSpanish;
    }

    public static Genre fromString(String text){
        for (Genre genre : Genre.values()){
            if (genre.genreOmdb.equalsIgnoreCase(text)){
                return genre;
            }
        }
        throw new IllegalArgumentException("Ningún genero encontrado: " + text);
    }

    public static Genre fromSpanish(String text){
        for (Genre genre : Genre.values()){
            if (genre.generoSpanish.equalsIgnoreCase(text)){
                return genre;
            }
        }
        throw new IllegalArgumentException("Ningún genero encontrado: " + text);
    }
}
