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
        if(song[4] != null && (song[4].replace(" ","").length() > 5 || song[4].replace(" ","").length() < 1)){return this;}
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
    
    public Playlist delete(String [] song){
        return null;
    }
    
    public Playlist select(String [] values){
        return null;
    }      

    /**
     * Retorna la cantidad de canciones en la playlist
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
      return "";
    }
    
    /**
     * 
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
}
