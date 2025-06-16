package model;

public enum Genre {
    ACCION("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIMEN("Crime");

    private String genreOmdb;

    Genre(String genreOmdb){
        this.genreOmdb = genreOmdb;
    }

    public static Genre fromString(String text){
        for (Genre genre : Genre.values()){
            if (genre.genreOmdb.equalsIgnoreCase(text)){
                return genre;
            }
        }
        throw new IllegalArgumentException("Ningún genero encontrado: " + text);
    }
}
