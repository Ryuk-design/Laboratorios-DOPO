//Each song is described by its title, artist, genre, duration, and rating.
//The title and artist are mandatory. The genre, duration, and rating may be unknown.
//The combination (title, artist) must be unique. Two songs cannot have the same title and artist.
//The duration (minutes) must be between 1 and 9.
//The rating must be between * and *****.
public class Playlist {
    private String[][] songs;
    
    /**
     * Metodo constructor de Playlist 
     * @param String[][] songs Recibe una lista de lista con canciones.
     */
    public Playlist(String [][] songs){
        this.songs = new String[0][5];
        for(int i = 0; i < songs.length; i++){
            this.add(songs[i]);
        }
    }
    
    /**
     * Agrega canciones a songs con algunas condiciones
     * @return En caso de que alguna condicion no se cumpla se retornara songs sin agregarle nada
     */
    public Playlist add(String [] song){
        if(song[0] == null){return this;}
        if(song[1] == null){return this;}
        if(song[3] != null && (song[3].matches("[1-9]")) == false){return this;}
        if(song[4] != null && (song[4].replace(" ","").length() > 5 
           || 
           song[4].replace(" ","").length() < 1)){return this;}
        for(int i = 0; i < songs.length; i++){
            if(songs[i][0].replace(" ","").toUpperCase().equals(song[0].replace(" ","").toUpperCase()) 
                &&
               songs[i][1].replace(" ","").toUpperCase().equals(song[1].replace(" ","").toUpperCase())){
                return this;
            }
        }
        String[][] newsongs = new String[songs.length + 1][5];
        for(int i = 0; i < songs.length; i++){
            for(int j = 0; j <= 4; j++){
                newsongs[i][j] = songs[i][j];
            }
        }
        newsongs[songs.length] = song;
        songs = newsongs;
        return this;
    }
    
    /**
     * Deletes a song from a playlist
     * @param song song to eliminate
     */
    public Playlist delete(String [] song){
        boolean is_on_playlist = false;
        for(int i = 0; i < songs.length; i++){
            if(songs[i][0].replace(" ","").toUpperCase().equals(song[0].replace(" ","").toUpperCase()) 
                 &&
                 songs[i][1].replace(" ","").toUpperCase().equals(song[1].replace(" ","").toUpperCase())){
                is_on_playlist = true;
            }
        }
        if(is_on_playlist == false){return this;}
        String[][] newsongs = new String[songs.length - 1][5];
        int k = 0;
        for(int i = 0; i < songs.length; i++){
            if(!(songs[i][0].replace(" ","").toUpperCase().equals(song[0].replace(" ","").toUpperCase()) 
                 &&
                 songs[i][1].replace(" ","").toUpperCase().equals(song[1].replace(" ","").toUpperCase()))){
                for(int j = 0; j <= 4; j++){   
                    newsongs[k][j] = songs[i][j];
                }
                k++;
            }
        }
        songs = newsongs;
        return this;
    }
    
    
    /**
     * filter the songs of a playlist by given values
     * @param values values to filter by
     */
    public Playlist select(String [] values){
        Playlist pl = new Playlist(new String[0][5]);
        for(int i = 0; i < songs.length; i++){
            boolean c = true;
            for(int j = 0; j <= 4; j++){
                String a = songs[i][j];
                String b = values[j];
                if(a == null && b == null){
                    continue;
                } else if(a == null || b == null){
                    c = false;
                    break;
                } else{
                    if(!a.replace(" ","").toUpperCase().equals(b.replace(" ","").toUpperCase())){
                        c = false;
                        break;
                    }
                }
            }
            if(c){
                pl.add(songs[i]);
            }
        }
        return pl;
    }      
    
    /**
     * Union two playlists
     * @return the union of two playlists
     */
    public Playlist union(Playlist other){
        Playlist res = new Playlist(new String[0][5]);
        for(int i = 0; i < this.songs.length; i++){
            res = res.add(this.songs[i]);
        }
        for(int i = 0; i < other.songs.length; i++){
            res = res.add(other.songs[i]);
        }
        return res;
    }
    
    /**
     * @return the intersection of two playlists
     */
    public Playlist intersection(Playlist other){
        Playlist res = new Playlist(new String[0][5]);
        for(int i = 0; i < this.songs.length; i++){
            for(int j = 0; j < other.songs.length; j++){
                if(this.songs[i][0].replace(" ","").toUpperCase().equals(other.songs[j][0].replace(" ","").toUpperCase())
                    && this.songs[i][1].replace(" ","").toUpperCase().equals(other.songs[j][1].replace(" ","").toUpperCase())){
                    res = res.add(this.songs[i]);
                }
            }
        }
        return res;
    }
    
    /**
     *@return the difference between two playlists 
     */
    public Playlist difference(Playlist other){
        Playlist res = new Playlist(new String[0][5]);
        for(int i = 0; i < this.songs.length; i++){
            res = res.add(this.songs[i]);
        }
        for(int i = 0; i < this.songs.length; i++){
            for(int j = 0; j < other.songs.length; j++)
                if(this.songs[i][0].replace(" ","").toUpperCase().equals(other.songs[j][0].replace(" ","").toUpperCase())
                    && 
                   this.songs[i][1].replace(" ","").toUpperCase().equals(other.songs[j][1].replace(" ","").toUpperCase())){
                    res = res.delete(res.songs[i]);
                }
        }
        return res;
    }
    
    /**
     * @return the size of a playlist
     */
    public int size(){
        return songs.length;
    }    
    
   
    // Songs are in uppercase with unnecessary spaces removed.
    // Columns are aligned and separated by three spaces.
    //TITLE    ARTIST          GENRE   DURATION   RATING
    //ONE      U2              ROCK           4   *****
    //NUMB     LINKIN PARK     ROCK           3
    //ALIVE    PEARL JAM       ROCK           5   ****
    //CREEP    RADIOHEAD       ROCK               *****
    //DREAMS   FLEETWOOD MAC   .              4   ****
    public String toString(){
        return null;
    }
    
    /**
     * compare two playlists
     * @return true if the two playlists are equal
     */
    public boolean equals(Playlist pl){
        if((this.size() == (pl.size())) == false){
            return false;
        }else{
            for(int i = 0; i < this.size(); i++){
                for(int j = 0; j <= 4; j++){
                    String a = this.songs[i][j];
                    String b = pl.songs[i][j];
                    if(a == null && b == null){continue;}
                    if(a == null || b == null){return false;}
                    if((this.songs[i][j].replace(" ","").toUpperCase().equals(pl.songs[i][j].replace(" ","").toUpperCase())) == false){
                    return false;
                    }
                }
            }
            return true;
        }
    }
    
    public boolean equals(Object o){
        return equals((Playlist)o);
    }
    
    public String[][] getSongs(){
        return songs;
    }
}
