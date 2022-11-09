package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpotifyJSON implements MusicSource {

    static SpotifyAccessor accessor;

    static {
        try {
            accessor = new SpotifyAccessor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        @Override
        public List<Artist> artistOf (String id) throws Exception {
            List<Artist> artists = new ArrayList<>();
            String response = accessor.get("/artists/" + id, Map.of());
            System.out.println(response); //Para que muestre el json

            JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
            String name = jsonObject.get("name").getAsString();
            int popularity = jsonObject.get("popularity").getAsInt();
            JsonArray genres = jsonObject.get("genres").getAsJsonArray();
            int followers = jsonObject.get("followers").getAsJsonObject().get("total").getAsInt();


            artists.add(new Artist(id, name, popularity, genres.toString(), followers));
            return artists;
        }

        @Override
        public List<Album> albumOf (String id) throws Exception {
            List<Album> albums = new ArrayList<>();
            String response = accessor.get("/artists/" + id + "/albums?include_groups=album", Map.of());

            JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
            JsonArray items = jsonObject.get("items").getAsJsonArray();

            for (JsonElement item : items) {
                String title = item.getAsJsonObject().get("name").getAsString();
                JsonArray artists = item.getAsJsonObject().get("artists").getAsJsonArray();

                //Como artist es un array tenemos que recorrerlo para coger el nombre del artistas ("author")
                JsonArray author = new JsonArray();
                for (JsonElement artist : artists) {
                    String nameArtist = artist.getAsJsonObject().get("name").getAsString();
                    author.add(nameArtist);
                }
                String releaseDate = item.getAsJsonObject().get("release_date").getAsString();
                int totalTracks = item.getAsJsonObject().get("total_tracks").getAsInt();


                albums.add(new Album(title, author.toString(), releaseDate, totalTracks));
            }
            return albums;

        }


        @Override
        public List<Song> songOf (String id) throws Exception {
            List<Song> songs = new ArrayList<>();
            String response = accessor.get("/artists/" + id + "/top-tracks", Map.of("market","ES"));

            JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
            JsonArray tracks = jsonObject.get("tracks").getAsJsonArray();

            for (JsonElement track : tracks) {
                String title = track.getAsJsonObject().get("name").getAsString();
                JsonArray artists = track.getAsJsonObject().get("artists").getAsJsonArray();

                //Como artist es un array tenemos que recorrerlo para coger el nombre del artistas ("author")
                JsonArray author = new JsonArray();
                for (JsonElement artist : artists) {
                    String nameArtist = artist.getAsJsonObject().get("name").getAsString();
                    author.add(nameArtist);
                }
                int duration = track.getAsJsonObject().get("duration_ms").getAsInt();
                Boolean explicit = track.getAsJsonObject().get("explicit").getAsBoolean();



                songs.add(new Song(title, author.toString(), duration, explicit));
            }
            return songs;

        }


    }