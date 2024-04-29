import java.util.List;
import java.util.*;

public class Song {
    private String title;
    private String artist;
    private String album;
    private int duration; // in seconds

    // Constructor
    public Song(String title, String artist, String album, int duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Song [title=" + title + ", artist=" + artist + ", album=" + album + ", duration=" + duration + " seconds]";
    }
}

interface SongService {
    Song searchById(Integer songID);
    List<Song> searchByTitle(String title);
    List<Song> searchByAlbum(String album);
}

class RealSongService implements SongService {
    private Map<Integer, Song> songDatabase;

    public RealSongService() {
        // Initialize song database
        songDatabase = new HashMap<>();
        
        // Adding some songs to the database
        songDatabase.put(1, new Song("Song A", "Artist A", "Album A", 180));
        songDatabase.put(2, new Song("Song B", "Artist B", "Album B", 210));
        songDatabase.put(3, new Song("Song C", "Artist C", "Album C", 200));
        songDatabase.put(4, new Song("Song D", "Artist D", "Album D", 240));
        songDatabase.put(5, new Song("Song E", "Artist E", "Album E", 220));
    }

    @Override
    public Song searchById(Integer songID) {
        try {
            Thread.sleep(1000); // Simulate 1 second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return songDatabase.get(songID);
    }

    @Override
    public List<Song> searchByTitle(String title) {
        try {
            Thread.sleep(1000); // Simulate 1 second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Song> result = new ArrayList<>();
        for (Song song : songDatabase.values()) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                result.add(song);
            }
        }
        return result;
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        try {
            Thread.sleep(1000); // Simulate 1 second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Song> result = new ArrayList<>();
        for (Song song : songDatabase.values()) {
            if (song.getAlbum().equalsIgnoreCase(album)) {
                result.add(song);
            }
        }
        return result;
    }
}

class SongServiceProxy implements SongService {
    private RealSongService realService;
    private Map<Integer, Song> songCacheById;
    private Map<String, List<Song>> songCacheByTitle;
    private Map<String, List<Song>> songCacheByAlbum;

    public SongServiceProxy() {
        realService = new RealSongService();
        songCacheById = new HashMap<>();
        songCacheByTitle = new HashMap<>();
        songCacheByAlbum = new HashMap<>();
    }

    @Override
    public Song searchById(Integer songID) {
        if (songCacheById.containsKey(songID)) {
            return songCacheById.get(songID);
        }

        Song song = realService.searchById(songID);
        songCacheById.put(songID, song);
        return song;
    }

    @Override
    public List<Song> searchByTitle(String title) {
        if (songCacheByTitle.containsKey(title)) {
            return songCacheByTitle.get(title);
        }

        List<Song> songs = realService.searchByTitle(title);
        songCacheByTitle.put(title, songs);
        return songs;
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        if (songCacheByAlbum.containsKey(album)) {
            return songCacheByAlbum.get(album);
        }

        List<Song> songs = realService.searchByAlbum(album);
        songCacheByAlbum.put(album, songs);
        return songs;
    }
}


