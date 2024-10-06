package com.crio.jukebox.entites;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User Test")
public class UserTest {
    @Test
    @DisplayName("checkIfPlaylistExists should Return true If Playlist is Found")
    public void checkIfPlaylistExists_ShouldReturnTrue_GivenPlaylist(){
  
        String id = "1";
        String name = "MY_PLAYLIST_1";
        List<Artist> featureArtists = new ArrayList<Artist>()
        {
            {
                add(new Artist("Ed Sheeran"));
                add(new Artist("Cardi.B"));
            }
        };
   
        List<Song> songs =  new ArrayList<Song>()
        {
            {
             add(new Song("1" ,"South of the Border", "Pop",new Album("No.6 Collaborations Project"),featureArtists.get(0) ,featureArtists));
            }
        };

        Playlist playlist = new Playlist(id, name, songs);
        
        User user =  new User(id, name, new ArrayList<Playlist>(){{ add(playlist); }});
        
        Assertions.assertTrue(user.checkIfPlaylistExist(playlist));

    }
    
    @Test
   @DisplayName("checkIfPlaylistExists should Return False If No Playlist is Found")
   public void checkIfPlaylistExists_ShouldReturnFalse_GivenPlaylist(){
    
        String id = "1";
        String name = "MY_PLAYLIST_1";
        List<Artist> featureArtists = new ArrayList<Artist>()
        {
            {
                add(new Artist("Ed Sheeran"));
                add(new Artist("Cardi.B"));
            }
        };
   
        List<Song> songs =  new ArrayList<Song>()
        {
            {
             add(new Song("1" ,"South of the Border", "Pop",new Album("No.6 Collaborations Project"),featureArtists.get(0) ,featureArtists));
            }
        };
       
        Playlist playlist = new Playlist(id, name, songs);
        
        User user =  new User(id, name);
        
        Assertions.assertFalse(user.checkIfPlaylistExist(playlist));

   }
}

