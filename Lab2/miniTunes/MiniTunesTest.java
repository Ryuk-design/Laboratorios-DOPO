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
    
    @After
    public void tearDown()
    {
    }
    
    
}