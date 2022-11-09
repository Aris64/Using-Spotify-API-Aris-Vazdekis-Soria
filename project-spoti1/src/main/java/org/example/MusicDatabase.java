package org.example;

import java.sql.SQLException;

public interface MusicDatabase {
    void add(Album albums) throws SQLException;
    void add(Artist artist) throws SQLException;
    void add(Song song) throws SQLException;
}
