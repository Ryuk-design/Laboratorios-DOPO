import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MiniTunesTest.
 *
 * @authors  (Samuel Ahumada - Nerieth Villota)
 * @version (a version number or a date)
 */
public class MiniTunesTest
{
    /**
     * Default constructor for test class MiniTunesTest
     */
    public MiniTunesTest(){

    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
    }
    //Ciclo 1.
    // Define Method.
    @Test
    public void shouldNotDefineDuplicatePlaylistName(){
        String name = "RHCP";
        MiniTunes min = new MiniTunes();
        min.define(name);
        min.define(name);
        assertEquals(1, min.playlistsSize());
    }
    
    @Test
    public void shouldNotDefineNullPlaylistName(){
        String name = null;
        MiniTunes min = new MiniTunes();
        min.define(name);
        assertEquals(0, min.playlistsSize());
    }
    
    @Test
    public void shouldDefineNewPlaylistName(){
        String name = "Pink Floyd";
        MiniTunes min = new MiniTunes();
        min.define(name);
        assertTrue(min.containsPlaylistName(name));
    }
    // Assign Method.
    @Test
    public void shouldAssignPlaylistToName(){
        String name = "Dire Straits";
        MiniTunes min = new MiniTunes();
        String[][] s = {{"One", "U2", "Rock", "4", "*****"}};
        Playlist pl = new Playlist(s);
        min.define(name);
        min.assign(name, pl.getSongs());
        assertEquals(pl, min.getPlaylist(name));
    }
    
    @Test
    public void shouldNotAssignPlaylistToNotExistingName(){
        String name = "No Existo";
        MiniTunes min = new MiniTunes();
        String[][] s = {{"One", "U2", "Rock", "4", "*****"}};
        Playlist pl = new Playlist(s);
        min.assign(name, pl.getSongs());
        assertNull(min.getPlaylist(name));
    }
    
    @Test
    public void shouldReassignNewPlaylistToName(){
        String name = "Dire Straits";
        MiniTunes min = new MiniTunes();
        String[][] s = {{"One", "U2", "Rock", "4", "*****"}};
        String[][] o = {{"Numb", "Linkin Park", "Rock", "3", null}};
        Playlist pl = new Playlist(s);
        Playlist pla = new Playlist (o);
        min.define(name);
        min.assign(name, pl.getSongs());
        min.assign(name, pla.getSongs());
        assertEquals(pla, min.getPlaylist(name));
    }
    // playlistNames Method.
    
    @Test
    public void shouldReturnEmptyWhenNoPlaylistDefined(){
        MiniTunes min = new MiniTunes();
        String[] names = min.playlistsNames();
        assertEquals(0, names.length);
    }
    
    @Test
    public void shouldReturnArrayWithPlaylistNames(){
        String name = "Green Day";
        String name1 = "Queen";
        String name2 = "Gustavo Cerati";
        String name3 = "Rush";
        MiniTunes min = new MiniTunes();
        min.define(name);
        min.define(name1);
        min.define(name2);
        min.define(name3);
        assertEquals(4, min.playlistsNames().length);
    }
    // playlistsSongs Method.
    @Test
    public void shouldReturnSongs(){
        String name = "Dire Straits";
        MiniTunes min = new MiniTunes();
        String[][] s = {{"One", "U2", "Rock", "4", "*****"}};
        Playlist pl = new Playlist(s);
        min.define(name);
        min.assign(name, pl.getSongs());
        String[][] songs = min.playlistSongs(name);
        assertArrayEquals(s, songs);
    }
    
    @Test
    public void shouldReturnEmptyWhenNoExistingPlaylistNameOrNoPlaylistDefined(){
        MiniTunes min = new MiniTunes();
        String name = "No existo";
        String[][] songs = min.playlistSongs(name);
        assertEquals(0, songs.length);
    }
    
    @Test
    public void shouldReturnEmptyWhenNoPlaylistAssigned(){
        String name = "Dire Straits";
        MiniTunes min = new MiniTunes();
        min.define(name);
        String[][] songs = min.playlistSongs(name);
        assertEquals(0, songs.length);
    }
    // Ciclo 2
    // addSong Method.
    @Test
    //Each song is described by its title, artist, genre, duration, and rating.
    //The title and artist are mandatory. The genre, duration, and rating may be unknown.
    //The combination (title, artist) must be unique. Two songs cannot have the same title and artist.
    //The duration (minutes) must be between 1 and 9.
    //The rating must be between * and *****.
    public void shouldNotAddInvalidSong(){
        MiniTunes min = new MiniTunes();
        String name = "Rock";
        min.define(name); 
        // cancion necesaria para crear una playlist
        String[][] s = {{"Creep", "Radiohead", "Rock", null, "*****"}};
        min.assign(name, s);
        // tiene 7 de calificacion
        String[] song = {"One", "U2", "Rock", "4", "*******"};
        min.addSong(name, song);
        // tiene "Rock" en la duracion
        String[] song1 = {"Numb", "Linkin Park", "Rock", "Rock", null};
        min.addSong(name, song1);
        // no tiene artista definida
        String[] song2 = {"Creep", null, "Rock", null, "*****"};
        min.addSong(name, song2);
        // la cancion no tiene nombre
        String[] song3 = {null, "Fleetwood Mac", null, "4", "****"};
        min.addSong(name, song3);
        // cancion repetida
        String[] song4 = {"Creep", "Radiohead", "Rock", null, "*****"};
        min.addSong(name, song4);
        // cancion con duracion mayor a 9
        String[] song5 = {"Alive", "Pearl Jam", "Rock", "10", "****"};
        min.addSong(name, song5);
        assertEquals(1, min.getPlaylist(name).size());
    }
    
    @Test
    public void shouldAddValidSongToPlaylist(){
        MiniTunes min = new MiniTunes();
        String name = "Rock and psychedelic Rock";
        min.define(name); 
        String[][] s = {{"Creep", "Radiohead", "Rock", null, "*****"}};
        min.assign(name, s);
        String [] song = {"Time", "Pink Floyd", "psychedelic Rock", "7", "*****"};
        min.addSong(name, song);
        assertEquals(2, min.getPlaylist(name).size());
    }
    
    @Test
    public void shouldNotAddSongIfPlaylistNameDoesNotExist(){
        MiniTunes min = new MiniTunes();
        String name = "No existo"; 
        String [] song = {"Time", "Pink Floyd", "psychedelic Rock", "7", "*****"};
        min.addSong(name, song);
        assertFalse(min.ok());
    }
    
    @Test
    public void shouldNotAddSongIfPlaylistNotAssigned(){
        MiniTunes min = new MiniTunes();
        String name = "Rock Classics";
        String[] song = {"Time", "Pink Floyd", "Psychedelic Rock", "7", "*****"};
        min.define(name);
        min.addSong(name, song);
        assertFalse(min.ok());
    }
    // deleteSong Method.
    
    @Test
    public void shouldDeleteSongFromExistingPlaylist(){
        MiniTunes min = new MiniTunes();
        String name = "Rock Classics";
        min.define(name);
        String[] song = {"One", "U2", "Rock", "4", "*****"};
        String[][] s = {song};
        min.assign(name, s);
        min.deleteSong(name, song);
        assertEquals(0, min.getPlaylist(name).size());
    }
    
    @Test
    public void shouldNotDeleteSongFromNonExistingName(){
        MiniTunes min = new MiniTunes();
        String name = "No existo"; 
        String [] song = {"Time", "Pink Floyd", "psychedelic Rock", "7", "*****"};
        min.deleteSong(name, song);
        assertFalse(min.ok());
    }
    
    @Test
    public void shouldNotDeleteSongFromNullPlaylist(){
        MiniTunes min = new MiniTunes();
        String name = "Rock Classics";
        String[] song = {"Time", "Pink Floyd", "Psychedelic Rock", "7", "*****"};
        min.define(name);
        min.deleteSong(name, song);
        assertFalse(min.ok());
    }
    
    @Test
    public void shouldNotDeleteNonExistingSongInPlaylist(){
        MiniTunes min = new MiniTunes();
        String name = "Rock Classics";
        min.define(name);
        String[] song = {"One", "U2", "Rock", "4", "*****"};
        String[][] s = {song};
        String[] song1 = {"Creep", "Radiohead", "Rock", null, "*****"};
        min.assign(name, s);
        min.deleteSong(name, song1);
        assertEquals(1, min.getPlaylist(name).size());
    }
    
    @After
    public void tearDown()
    {
    }
    
    
}