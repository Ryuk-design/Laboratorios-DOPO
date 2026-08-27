import java.util.TreeMap;

/** MiniTunes.java
 * 
 * @author ESCUELA 2026-02
 */
    
public class MiniTunes{
    
    private TreeMap<String,Playlist> playlists;
    
    public MiniTunes(){
        playlists = new TreeMap<String, Playlist>();
    }

    /**
     * Define a new playlist name
     * @author Samuel Ahumada
     * @param a   Name
     * @return If the name is null or if the name already exist, the method do nothing.
     */
    public void define(String name){
        if(name == null){return;}
        if(playlists.containsKey(name)){return;}
        playlists.put(name, null);
    }
     
    /**
     * Assign a playlist to an existing playlist name
     * @author Samuel Ahumada
     * @param a : Name
     * @return If trying to assign a non existing name
     */
    public void assign(String a, String[][] playlist){
        if(!playlists.containsKey(a)){return;}
        playlists.put(a, new Playlist(playlist));
    }    
    
    /**
     * @author Samuel Ahumada
     * @return an array with the names of the playlists
     */
    public String[] playlistsNames(){
        if(playlists.size() == 0){return new String[0];}
        String[] names = new String[playlists.size()];
        int i = 0;
        for(String name : playlists.keySet()){
            names[i] = name;
            i++;
        }
        return names;
    }
    
    /**
     * @param name of the playlist
     * @return the songs of a playlist
     */
    public String[][] playlistSongs(String name){
        if(playlists.size() == 0){return new String[0][0];}
        if(!playlists.containsKey(name)){return new String[0][0];}
        if(playlists.get(name) == null){return new String[0][0];}
        return playlists.get(name).getSongs();
    }
    
    //Return a playlist's size
    public int size(String a){
        return -1;
    }
    
    //Returns the playlist names in alphabetical order. comma-separated
    public String toString(){
        return null;
    }
    
    // Returns the string representation of a playlist.
    public String toString(String name){
        return null;
    }    
    
    //Assigns the value of a unary operation to a playlist name
    // a = b op parameters
    //The operator characters are: 'a' (add) , 'd' (delete),'s'(select)
    //For add and delete, the values correspond to the song data. For select, the parameters define the search pattern.
    public void assignUnary(String a, String b, char op, String [] values){
    }
      
    
    //Assigns the value of a binary operation to a playlist name
    // a = b op c
    //The operator characters are:  'u' union, 'i' intersection, 'd' difference
    //Songs preserve their original order in the resulting playlist.
    public void assignBinary(String a, String b, char op, String c){
    }
  
   
    //If the last operation was successfully completed
    public boolean ok(){
        return false;
    }
    
    public boolean containsPlaylistName(String name){
        return playlists.containsKey(name);
    }
    
    public int playlistsSize(){
        return playlists.size();
    }
    
    public Playlist getPlaylist(String name){
        return playlists.get(name);
    }
}
    



