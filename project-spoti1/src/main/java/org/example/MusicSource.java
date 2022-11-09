package org.example;

import java.util.List;

public interface MusicSource {

    List<Artist> artistOf(String id) throws Exception;
    List<Album> albumOf(String id) throws Exception;
    List<Song> songOf(String id) throws Exception;

}