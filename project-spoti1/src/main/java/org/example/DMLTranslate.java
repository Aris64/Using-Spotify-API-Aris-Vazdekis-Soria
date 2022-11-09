package org.example;

//PARA INSERTAR DENTRO DE LA TABLA

public class DMLTranslate {
    private static final String INSERT_ARTIST =
            "INSERT INTO artists(id, name, popularity, genres, followers) VALUES('%s', '%s', '%d', '%s', '%d');";
    public static String insertStatementOfArtist(Artist artist){
        return String.format(INSERT_ARTIST,
                artist.id,
                artist.name,
                artist.popularity,
                artist.genres,
                artist.followers);
    }


    private static final String INSERT_ALBUM =
            "INSERT INTO albums(title, author, releaseDate, totalTracks) VALUES('%s', '%s', '%s', '%d')";
    public static String insertStatementOfAlbum(Album album){
        return String.format(INSERT_ALBUM,
                album.title,
                album.author,
                album.ReleaseDate,
                album.totalTracks);
    }

    private static final String INSERT_SONG =
            "INSERT INTO songs(title, author, duration, explicit) VALUES('%s', '%s', '%d', '%s')";
    public static String insertStatementOfsong(Song song){
        return String.format(INSERT_SONG,
                song.title,
                song.author,
                song.duration,
                song.explicit);
    }

}
