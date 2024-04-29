
public class SongDriver {

	public static void main(String[] args) {
		SongService songService = new SongServiceProxy();

        // Search for songs by ID
        System.out.println("Searching for song with ID 1:");
        System.out.println(songService.searchById(1));
        
        
        // Search for songs by title
        System.out.println("\nSearching for songs with title 'Song B':");
        System.out.println(songService.searchByTitle("Song B"));

        // Search for songs by album
        System.out.println("\nSearching for songs with album 'Album C':");
        System.out.println(songService.searchByAlbum("Album C"));

        // Re-search for the same song ID to demonstrate caching
        System.out.println("\nRe-searching for song with ID 1):");
        System.out.println(songService.searchById(1));

        // Re-search for the same song title to demonstrate caching
        System.out.println("\nRe-searching for songs with title 'Song B':");
        System.out.println(songService.searchByTitle("Song B"));

        // Re-search for the same album to demonstrate caching
        System.out.println("\nRe-searching for songs with album 'Album C':");
        System.out.println(songService.searchByAlbum("Album C"));
	}

}
