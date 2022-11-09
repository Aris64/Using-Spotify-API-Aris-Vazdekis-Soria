package org.example;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {


        MusicSource source = new SpotifyJSON();
        List<String> Ids = Arrays.asList("0L8ExT028jH3ddEcZwqJJ5", "6IRouO5mvvfcyxtPDKMYFN", "4xls23Ye9WR9yy3yYMpAMm", "6olE6TJLqED3rqDCT0FyPh", "7Ey4PD4MYsKc5I2dolUwbH");
        for (String id: Ids) {
            List<Artist> artistList = source.artistOf(id);
            List<Album> albumList = source.albumOf(id);
            List<Song> songList = source.songOf(id);

            MusicDatabase musicDatabase = new SqliteMusicDatabase();

            // Para añadir los objetos a "TableArtists"
            for (Artist artist : artistList) {
                musicDatabase.add(artist);
            }

            // Para añadir los objetos a "TableAlbums"
            for (Album album : albumList) {
                musicDatabase.add(album);
            }

            // Para añadir los objetos a "TableSongs"
            for (Song song : songList) {
                musicDatabase.add(song);
            }

        }
    }


}