import java.util.TreeMap;

/** MiniTunes.java
 * 
 * @author ESCUELA 2026-02
 */
    
public class MiniTunes{
    
    private TreeMap<String,Playlist> playlists;
    private boolean lastOperationOk;
    
    public MiniTunes(){
        playlists = new TreeMap<String, Playlist>();
        lastOperationOk = true;
    }

    /**
     * Define a new playlist name
     * @param a   Name
     * @return If the name is null or if the name already exist, the method do nothing.
     */
    public void define(String name){
        if(name == null){
            lastOperationOk = false;
            return;
        }
        if(playlists.containsKey(name)){
            lastOperationOk = false;
            return;
        }
        playlists.put(name, null);
        lastOperationOk = true;
    }
     
    /**
     * Assign a playlist to an existing playlist name
     * @param a : Name
     * @return If trying to assign a non existing name
     */
    public void assign(String a, String[][] playlist){
        if(!playlists.containsKey(a)){
            lastOperationOk = false;
            return;
        }
        playlists.put(a, new Playlist(playlist));
        lastOperationOk = true;
    }    
    
    /**
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
        if(!playlists.containsKey(name)){
            lastOperationOk = false;
            return new String[0][0];
        }
        if(playlists.get(name) == null){return new String[0][0];}
        lastOperationOk = true;
        return playlists.get(name).getSongs();
    }
    
    /**
     * Add a song on an existing playlist
     *@param name Key of the playlist
     *@param String [] song  song to add
     */
    public void addSong(String name, String[] song){
        if(!playlists.containsKey(name)){
            lastOperationOk = false;
            return;        
        }
        if(playlists.get(name) == null){
            lastOperationOk = false;
            return;
        }
        playlists.get(name).add(song);
        lastOperationOk = true;
    }
    
    /**
     *Delete a song on an existing playlist
     *@param name Key to the playlist
     *@param String [] song  song to delete
     */
    public void deleteSong(String name, String[] song){
        if(!playlists.containsKey(name)){
            lastOperationOk = false;
            return;        
        }
        if(playlists.get(name) == null){
            lastOperationOk = false;
            return;
        }
        playlists.get(name).delete(song);
        lastOperationOk = true;
    }
    
    /**
     * filters songs from a playlist based on given initial values
     * @param name Key to the playlist
     */
    public Playlist select(String name, String[] values){
        if(playlists.containsKey(name) == false){
            lastOperationOk = false;
            return null;   
        }
        if(playlists.get(name) == null){
            lastOperationOk = false;
            return null;
        }
        if(values[0] == null){
            lastOperationOk = false;
            return null;
        }
        if(values[1] == null){
            lastOperationOk = false;
            return null; 
        }
        lastOperationOk = true;
        return playlists.get(name).select(values);
    }
    
    //Return a playlist's size
    public int size(String a){
        return playlists.get(a).size();
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
        if(!playlists.containsKey(b)){
            lastOperationOk = false;
            return;
        }
        if(playlists.get(b) == null){
            lastOperationOk = false;
            return;
        }
        Playlist plb = playlists.get(b);
        Playlist res;
        switch(op){
            case 'a':
                res = plb.add(values);
                break;
            case 'd':
                res = plb.delete(values);
                break;
            case 's':
                res = plb.select(values);
                break;
            default:
                lastOperationOk = false;
                return;
        }
        define(a);
        assign(a, res.getSongs());
        lastOperationOk = true;
    }
    
    //Assigns the value of a binary operation to a playlist name
    // a = b op c
    //The operator characters are:  'u' union, 'i' intersection, 'd' difference
    //Songs preserve their original order in the resulting playlist.
    
    public void assignBinary(String a, String b, char op, String c){
        if(!(playlists.containsKey(b) && playlists.containsKey(c))){
        lastOperationOk = false;
        return;
        }
        if(playlists.get(b) == null || playlists.get(c) == null){
        lastOperationOk = false;
        return;
        }
        Playlist plb = playlists.get(b);
        Playlist plc = playlists.get(c);
        Playlist res;    
        switch(op){
            case 'u':
                res = plb.union(plc);
                break;
            case 'i':
                res = plb.intersection(plc);
                break;
            case 'd':
                res = plb.difference(plc);
                break;
            default:
                lastOperationOk = false;
                return;
        }
        define(a);
        assign(a, res.getSongs());
        lastOperationOk = true;
    }
  
    public boolean containsPlaylistName(String name){
        return playlists.containsKey(name);
    }
    //If the last operation was successfully completed
    public boolean ok(){
        return lastOperationOk;
    }
    
    public int playlistsSize(){
        return playlists.size();
    }
    
    public Playlist getPlaylist(String name){
        return playlists.get(name);
    }
}